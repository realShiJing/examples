package com.nchu.java8.jdk.longadder;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file:
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

import java.util.function.LongBinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A package-local class holding common representation and mechanics
 * for classes supporting dynamic striping on 64bit values. The class
 * extends Number so that concrete subclasses must publicly do so.
 */
@SuppressWarnings("serial")
abstract class Striped64 extends Number {
    /*
     * This class maintains a lazily-initialized table of atomically
     * updated variables, plus an extra "base" field. The table size
     * is a power of two. Indexing uses masked per-thread hash codes.
     * Nearly all declarations in this class are package-private,
     * accessed directly by subclasses.
     *
     * Table entries are of class Cell; a variant of AtomicLong padded
     * (via @sun.misc.Contended) to reduce cache contention. Padding
     * is overkill for most Atomics because they are usually
     * irregularly scattered in memory and thus don't interfere much
     * with each other. But Atomic objects residing in arrays will
     * tend to be placed adjacent to each other, and so will most
     * often share cache lines (with a huge negative performance
     * impact) without this precaution.
     *
     * In part because Cells are relatively large, we avoid creating
     * them until they are needed.  When there is no contention, all
     * updates are made to the base field.  Upon first contention (a
     * failed CAS on base update), the table is initialized to size 2.
     * The table size is doubled upon further contention until
     * reaching the nearest power of two greater than or equal to the
     * number of CPUS. Table slots remain empty (null) until they are
     * needed.
     *
     * A single spinlock ("cellsBusy") is used for initializing and
     * resizing the table, as well as populating slots with new Cells.
     * There is no need for a blocking lock; when the lock is not
     * available, threads try other slots (or the base).  During these
     * retries, there is increased contention and reduced locality,
     * which is still better than alternatives.
     *
     * The Thread probe fields maintained via ThreadLocalRandom serve
     * as per-thread hash codes. We let them remain uninitialized as
     * zero (if they come in this way) until they contend at slot
     * 0. They are then initialized to values that typically do not
     * often conflict with others.  Contention and/or table collisions
     * are indicated by failed CASes when performing an update
     * operation. Upon a collision, if the table size is less than
     * the capacity, it is doubled in size unless some other thread
     * holds the lock. If a hashed slot is empty, and lock is
     * available, a new Cell is created. Otherwise, if the slot
     * exists, a CAS is tried.  Retries proceed by "double hashing",
     * using a secondary hash (Marsaglia XorShift) to try to find a
     * free slot.
     *
     * The table size is capped because, when there are more threads
     * than CPUs, supposing that each thread were bound to a CPU,
     * there would exist a perfect hash function mapping threads to
     * slots that eliminates collisions. When we reach capacity, we
     * search for this mapping by randomly varying the hash codes of
     * colliding threads.  Because search is random, and collisions
     * only become known via CAS failures, convergence can be slow,
     * and because threads are typically not bound to CPUS forever,
     * may not occur at all. However, despite these limitations,
     * observed contention rates are typically low in these cases.
     *
     * It is possible for a Cell to become unused when threads that
     * once hashed to it terminate, as well as in the case where
     * doubling the table causes no thread to hash to it under
     * expanded mask.  We do not try to detect or remove such cells,
     * under the assumption that for long-running instances, observed
     * contention levels will recur, so the cells will eventually be
     * needed again; and for short-lived ones, it does not matter.
     */

    /**
     * Padded variant of AtomicLong supporting only raw accesses plus CAS.
     *
     * JVM intrinsics note: It would be possible to use a release-only
     * form of CAS here, if it were provided.
     */
    @sun.misc.Contended static final class Cell {
        volatile long value;
        Cell(long x) { value = x; }
        final boolean cas(long cmp, long val) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
        }

        // Unsafe mechanics
        private static final sun.misc.Unsafe UNSAFE;
        private static final long valueOffset;
        static {
            try {
                UNSAFE = sun.misc.Unsafe.getUnsafe();
                Class<?> ak = Cell.class;
                valueOffset = UNSAFE.objectFieldOffset
                        (ak.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /** Number of CPUS, to place bound on table size */
    //表示当前计算机CPU数量，什么用？ 控制cells数组长度的一个关键条件
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    /**
     * Table of cells. When non-null, size is a power of 2.
     */
    transient volatile Cell[] cells;

    /**
     * Base value, used mainly when there is no contention, but also as
     * a fallback during table initialization races. Updated via CAS.
     * 没有发生过竞争时，数据会累加到 base上 | 当cells扩容时，需要将数据写到base中
     */
    transient volatile long base;

    /**
     * Spinlock (locked via CAS) used when resizing and/or creating Cells.
     * 初始化cells或者扩容cells都需要获取锁，0 表示无锁状态，1 表示其他线程已经持有锁了
     */
    transient volatile int cellsBusy;

    /**
     * Package-private default constructor
     */
    Striped64() {
    }

    /**
     * CASes the base field.
     */
    final boolean casBase(long cmp, long val) {
        return UNSAFE.compareAndSwapLong(this, BASE, cmp, val);
    }

    /**
     * CASes the cellsBusy field from 0 to 1 to acquire lock.
     * 通过CAS方式获取锁
     */
    final boolean casCellsBusy() {
        return UNSAFE.compareAndSwapInt(this, CELLSBUSY, 0, 1);
    }

    /**
     * Returns the probe value for the current thread.
     * Duplicated from ThreadLocalRandom because of packaging restrictions.
     *
     * 获取当前线程的Hash值
     */
    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }

    /**
     * Pseudo-randomly advances and records the given probe value for the
     * given thread.
     * Duplicated from ThreadLocalRandom because of packaging restrictions.
     *
     * 重置当前线程的Hash值
     */
    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
        return probe;
    }

    /**
     * Handles cases of updates involving initialization, resizing,
     * creating new Cells, and/or contention. See above for
     * explanation. This method suffers the usual non-modularity
     * problems of optimistic retry code, relying on rechecked sets of
     * reads.
     *
     * @param x the value
     * @param fn the update function, or null for add (this convention
     * avoids the need for an extra field or function in LongAdder).
     * @param wasUncontended false if CAS failed before call
     */
    //都有哪些情况会调用？
    //1.true->说明 cells 未初始化，也就是多线程写base发生竞争了[重试|初始化cells]
    //2.true-> 说明当前线程对应下标的cell为空，需要创建 longAccumulate 支持
    //3.true->表示cas失败，意味着当前线程对应的cell 有竞争[重试|扩容]

    // wasUncontended：只有cells初始化之后，并且当前线程 竞争修改失败，才会是false
    final void longAccumulate(long x, LongBinaryOperator fn,
                              boolean wasUncontended) {
        //h 表示线程hash值
        int h;
        //条件成立：说明当前线程 还未分配hash值
        if ((h = getProbe()) == 0) {
            //给当前线程分配hash值
            ThreadLocalRandom.current(); // force initialization
            //取出当前线程的hash值 赋值给h
            h = getProbe();
            //为什么？ 因为默认情况下 当前线程 肯定是写入到了 cells[0] 位置。 不把它当做一次真正的竞争
            wasUncontended = true;
        }

        //表示扩容意向 false 一定不会扩容，true 可能会扩容。
        boolean collide = false;                // True if last slot nonempty

        //自旋
        for (;;) {
            //as 表示cells引用
            //a 表示当前线程命中的cell
            //n 表示cells数组长度
            //v 表示 期望值
            Cell[] as; Cell a; int n; long v;

            //CASE1: 表示cells已经初始化了，当前线程应该将数据写入到对应的cell中
            if ((as = cells) != null && (n = as.length) > 0) {
                //2.true-> 说明当前线程对应下标的cell为空，需要创建 longAccumulate 支持
                //3.true->表示cas失败，意味着当前线程对应的cell 有竞争[重试|扩容]

                //CASE1.1:true->表示当前线程对应的下标位置的cell为null，需要创建new Cell
                if ((a = as[(n - 1) & h]) == null) {

                    //true->表示当前锁 未被占用  false->表示锁被占用
                    if (cellsBusy == 0) {       // Try to attach new Cell

                        //拿当前的x创建Cell
                        Cell r = new Cell(x);   // Optimistically create

                        //条件一：true->表示当前锁 未被占用  false->表示锁被占用
                        //条件二：true->表示当前线程获取锁成功  false->当前线程获取锁失败..
                        if (cellsBusy == 0 && casCellsBusy()) {
                            //是否创建成功 标记
                            boolean created = false;
                            try {               // Recheck under lock
                                //rs 表示当前cells 引用
                                //m 表示cells长度
                                //j 表示当前线程命中的下标
                                Cell[] rs; int m, j;

                                //条件一 条件二 恒成立
                                //rs[j = (m - 1) & h] == null 为了防止其它线程初始化过 该位置，然后当前线程再次初始化该位置
                                //导致丢失数据
                                if ((rs = cells) != null &&
                                        (m = rs.length) > 0 &&
                                        rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created)
                                break;
                            continue;           // Slot is now non-empty
                        }
                    }

                    //扩容意向 强制改为了false
                    collide = false;
                }
                // CASE1.2：
                // wasUncontended：只有cells初始化之后，并且当前线程 竞争修改失败，才会是false
                else if (!wasUncontended)       // CAS already known to fail
                    wasUncontended = true;      // Continue after rehash
                    //CASE 1.3：当前线程rehash过hash值，然后新命中的cell不为空
                    //true -> 写成功,退出循环
                    //false -> 表示rehash之后命中的新的cell 也有竞争 重试1次 再重试1次
                else if (a.cas(v = a.value, ((fn == null) ? v + x :
                        fn.applyAsLong(v, x))))
                    break;
                    //CASE 1.4:
                    //条件一：n >= NCPU true->扩容意向 改为false，表示不扩容了  false-> 说明cells数组还可以扩容
                    //条件二：cells != as true->其它线程已经扩容过了，当前线程rehash之后重试即可
                else if (n >= NCPU || cells != as)
                    //扩容意向 改为false，表示不扩容了
                    collide = false;            // At max size or stale
                    //CASE 1.5:
                    //!collide = true 设置扩容意向 为true 但是不一定真的发生扩容
                else if (!collide)
                    collide = true;
                    //CASE 1.6:真正扩容的逻辑
                    //条件一：cellsBusy == 0 true->表示当前无锁状态，当前线程可以去竞争这把锁
                    //条件二：casCellsBusy true->表示当前线程 获取锁 成功，可以执行扩容逻辑
                    // false->表示当前时刻有其它线程在做扩容相关的操作。
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        //cells == as
                        if (cells == as) {      // Expand table unless stale
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i)
                                rs[i] = as[i];
                            cells = rs;
                        }
                    } finally {
                        //释放锁
                        cellsBusy = 0;
                    }
                    collide = false;
                    continue;                   // Retry with expanded table
                }

                //重置当前线程Hash值
                h = advanceProbe(h);
            }
            //CASE2：前置条件cells还未初始化 as 为null
            //条件一：true 表示当前未加锁
            //条件二：cells == as？因为其它线程可能会在你给as赋值之后修改了 cells
            //条件三：true 表示获取锁成功 会把cellsBusy = 1,false 表示其它线程正在持有这把锁
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    //cells == as? 防止其它线程已经初始化了，当前线程再次初始化 导致丢失数据
                    if (cells == as) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(x);
                        cells = rs;
                        init = true;
                    }
                } finally {
                    cellsBusy = 0;
                }
                if (init)
                    break;
            }
            //CASE3：
            //1.当前cellsBusy加锁状态，表示其它线程正在初始化cells，所以当前线程将值累加到base
            //2.cells被其它线程初始化后，当前线程需要将数据累加到base
            else if (casBase(v = base, ((fn == null) ? v + x :
                    fn.applyAsLong(v, x))))
                break;                          // Fall back on using base
        }
    }

    /**
     * Same as longAccumulate, but injecting long/double conversions
     * in too many places to sensibly merge with long version, given
     * the low-overhead requirements of this class. So must instead be
     * maintained by copy/paste/adapt.
     */
    final void doubleAccumulate(double x, DoubleBinaryOperator fn,
                                boolean wasUncontended) {
        int h;
        if ((h = getProbe()) == 0) {
            ThreadLocalRandom.current(); // force initialization
            h = getProbe();
            wasUncontended = true;
        }
        boolean collide = false;                // True if last slot nonempty
        for (;;) {
            Cell[] as; Cell a; int n; long v;
            if ((as = cells) != null && (n = as.length) > 0) {
                if ((a = as[(n - 1) & h]) == null) {
                    if (cellsBusy == 0) {       // Try to attach new Cell
                        Cell r = new Cell(Double.doubleToRawLongBits(x));
                        if (cellsBusy == 0 && casCellsBusy()) {
                            boolean created = false;
                            try {               // Recheck under lock
                                Cell[] rs; int m, j;
                                if ((rs = cells) != null &&
                                        (m = rs.length) > 0 &&
                                        rs[j = (m - 1) & h] == null) {
                                    rs[j] = r;
                                    created = true;
                                }
                            } finally {
                                cellsBusy = 0;
                            }
                            if (created)
                                break;
                            continue;           // Slot is now non-empty
                        }
                    }
                    collide = false;
                }
                else if (!wasUncontended)       // CAS already known to fail
                    wasUncontended = true;      // Continue after rehash
                else if (a.cas(v = a.value,
                        ((fn == null) ?
                                Double.doubleToRawLongBits
                                        (Double.longBitsToDouble(v) + x) :
                                Double.doubleToRawLongBits
                                        (fn.applyAsDouble
                                                (Double.longBitsToDouble(v), x)))))
                    break;
                else if (n >= NCPU || cells != as)
                    collide = false;            // At max size or stale
                else if (!collide)
                    collide = true;
                else if (cellsBusy == 0 && casCellsBusy()) {
                    try {
                        if (cells == as) {      // Expand table unless stale
                            Cell[] rs = new Cell[n << 1];
                            for (int i = 0; i < n; ++i)
                                rs[i] = as[i];
                            cells = rs;
                        }
                    } finally {
                        cellsBusy = 0;
                    }
                    collide = false;
                    continue;                   // Retry with expanded table
                }
                h = advanceProbe(h);
            }
            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
                boolean init = false;
                try {                           // Initialize table
                    if (cells == as) {
                        Cell[] rs = new Cell[2];
                        rs[h & 1] = new Cell(Double.doubleToRawLongBits(x));
                        cells = rs;
                        init = true;
                    }
                } finally {
                    cellsBusy = 0;
                }
                if (init)
                    break;
            }
            else if (casBase(v = base,
                    ((fn == null) ?
                            Double.doubleToRawLongBits
                                    (Double.longBitsToDouble(v) + x) :
                            Double.doubleToRawLongBits
                                    (fn.applyAsDouble
                                            (Double.longBitsToDouble(v), x)))))
                break;                          // Fall back on using base
        }
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long BASE;
    private static final long CELLSBUSY;
    private static final long PROBE;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> sk = Striped64.class;
            BASE = UNSAFE.objectFieldOffset
                    (sk.getDeclaredField("base"));
            CELLSBUSY = UNSAFE.objectFieldOffset
                    (sk.getDeclaredField("cellsBusy"));
            Class<?> tk = Thread.class;
            PROBE = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomProbe"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
