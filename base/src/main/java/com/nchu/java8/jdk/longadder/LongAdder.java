/*
package com.nchu.java8.jdk.longadder;*/
/*
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
 *//*


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
 *//*


import java.io.Serializable;

*/
/**
 * One or more variables that together maintain an initially zero
 * {@code long} sum.  When updates (method {@link #add}) are contended
 * across threads, the set of variables may grow dynamically to reduce
 * contention. Method {@link #sum} (or, equivalently, {@link
 * #longValue}) returns the current total combined across the
 * variables maintaining the sum.
 *
 * <p>This class is usually preferable to {@link AtomicLong} when
 * multiple threads update a common sum that is used for purposes such
 * as collecting statistics, not for fine-grained synchronization
 * control.  Under low update contention, the two classes have similar
 * characteristics. But under high contention, expected throughput of
 * this class is significantly higher, at the expense of higher space
 * consumption.
 *
 * <p>LongAdders can be used with a {@link
 * java.util.concurrent.ConcurrentHashMap} to maintain a scalable
 * frequency map (a form of histogram or multiset). For example, to
 * add a count to a {@code ConcurrentHashMap<String,LongAdder> freqs},
 * initializing if not already present, you can use {@code
 * freqs.computeIfAbsent(k -> new LongAdder()).increment();}
 *
 * <p>This class extends {@link Number}, but does <em>not</em> define
 * methods such as {@code equals}, {@code hashCode} and {@code
 * compareTo} because instances are expected to be mutated, and so are
 * not useful as collection keys.
 *
 * @since 1.8
 * @author Doug Lea
 *//*

public class LongAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    */
/**
     * Creates a new adder with initial sum of zero.
     *//*

    public LongAdder() {
    }

    */
/**
     * Adds the given value.
     *
     * @param x the value to add
     *//*

    public void add(long x) {
        //as 表示cells引用
        //b 表示获取的base值
        //v 表示 期望值
        //m 表示 cells 数组的长度
        //a 表示当前线程命中的cell单元格
        Cell[] as; long b, v; int m; Cell a;

        //条件一：true->表示cells已经初始化过了,当前线程应该将数据写入到对应的cell中
        //       false->表示cells未初始化，当前所有线程应该将数据写到base中

        //条件二：false->表示当前线程cas替换数据成功，
        //       true->表示发生竞争了，可能需要重试 或者 扩容
        if ((as = cells) != null || !casBase(b = base, b + x)) {
            //什么时候会进来？
            //1.true->表示cells已经初始化过了,当前线程应该将数据写入到对应的cell中
            //2.true->表示发生竞争了，可能需要重试 或者 扩容



            //true -> 未竞争  false->发生竞争
            boolean uncontended = true;

            //条件一：true->说明 cells 未初始化，也就是多线程写base发生竞争了
            //       false->说明 cells 已经初始化了，当前线程应该是 找自己的cell 写值

            //条件二：getProbe() 获取当前线程的hash值   m表示cells长度-1 cells长度 一定是2的次方数   15= b1111
            //       true-> 说明当前线程对应下标的cell为空，需要创建 longAccumulate 支持
            //       false-> 说明当前线程对应的cell 不为空，说明 下一步想要将x值 添加到cell中。

            //条件三：true->表示cas失败，意味着当前线程对应的cell 有竞争
            //       false->表示cas成功
            if (as == null || (m = as.length - 1) < 0 ||
                    (a = as[getProbe() & m]) == null ||
                    !(uncontended = a.cas(v = a.value, v + x)))
                //都有哪些情况会调用？
                //1.true->说明 cells 未初始化，也就是多线程写base发生竞争了[重试|初始化cells]
                //2.true-> 说明当前线程对应下标的cell为空，需要创建 longAccumulate 支持
                //3.true->表示cas失败，意味着当前线程对应的cell 有竞争[重试|扩容]
                longAccumulate(x, null, uncontended);
        }
    }

    */
/**
     * Equivalent to {@code add(1)}.
     *//*

    //+1
    public void increment() {
        add(1L);
    }

    */
/**
     * Equivalent to {@code add(-1)}.
     *//*

    //-1
    public void decrement() {
        add(-1L);
    }

    */
/**
     * Returns the current sum.  The returned value is <em>NOT</em> an
     * atomic snapshot; invocation in the absence of concurrent
     * updates returns an accurate result, but concurrent updates that
     * occur while the sum is being calculated might not be
     * incorporated.
     *
     * @return the sum
     *//*

    public long sum() {
        Cell[] as = cells; Cell a;
        long sum = base;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    sum += a.value;
            }
        }
        return sum;
    }

    */
/**
     * Resets variables maintaining the sum to zero.  This method may
     * be a useful alternative to creating a new adder, but is only
     * effective if there are no concurrent updates.  Because this
     * method is intrinsically racy, it should only be used when it is
     * known that no threads are concurrently updating.
     *//*

    public void reset() {
        Cell[] as = cells; Cell a;
        base = 0L;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    a.value = 0L;
            }
        }
    }

    */
/**
     * Equivalent in effect to {@link #sum} followed by {@link
     * #reset}. This method may apply for example during quiescent
     * points between multithreaded computations.  If there are
     * updates concurrent with this method, the returned value is
     * <em>not</em> guaranteed to be the final value occurring before
     * the reset.
     *
     * @return the sum
     *//*

    public long sumThenReset() {
        Cell[] as = cells; Cell a;
        long sum = base;
        base = 0L;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    sum += a.value;
                    a.value = 0L;
                }
            }
        }
        return sum;
    }

    */
/**
     * Returns the String representation of the {@link #sum}.
     * @return the String representation of the {@link #sum}
     *//*

    public String toString() {
        return Long.toString(sum());
    }

    */
/**
     * Equivalent to {@link #sum}.
     *
     * @return the sum
     *//*

    public long longValue() {
        return sum();
    }

    */
/**
     * Returns the {@link #sum} as an {@code int} after a narrowing
     * primitive conversion.
     *//*

    public int intValue() {
        return (int)sum();
    }

    */
/**
     * Returns the {@link #sum} as a {@code float}
     * after a widening primitive conversion.
     *//*

    public float floatValue() {
        return (float)sum();
    }

    */
/**
     * Returns the {@link #sum} as a {@code double} after a widening
     * primitive conversion.
     *//*

    public double doubleValue() {
        return (double)sum();
    }

    */
/**
     * Serialization proxy, used to avoid reference to the non-public
     * Striped64 superclass in serialized forms.
     * @serial include
     *//*

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 7249069246863182397L;

        */
/**
         * The current value returned by sum().
         * @serial
         *//*

        private final long value;

        SerializationProxy(LongAdder a) {
            value = a.sum();
        }

        */
/**
         * Return a {@code LongAdder} object with initial state
         * held by this proxy.
         *
         * @return a {@code LongAdder} object with initial state
         * held by this proxy.
         *//*

        private Object readResolve() {
            LongAdder a = new LongAdder();
            a.base = value;
            return a;
        }
    }

    */
/**
     * Returns a
     * <a href="../../../../serialized-form.html#java.util.concurrent.atomic.LongAdder.SerializationProxy">
     * SerializationProxy</a>
     * representing the state of this instance.
     *
     * @return a {@link SerializationProxy}
     * representing the state of this instance
     *//*

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    */
/**
     * @param s the stream
     * @throws java.io.InvalidObjectException always
     *//*

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.InvalidObjectException {
        throw new java.io.InvalidObjectException("Proxy required");
    }

}
*/
