package com.nchu.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * ClassName: MiniReentrantLock
 * Description:
 * date: 2020/3/28 14:04
 */
public class MiniReentrantLock implements Lock{
    /**
     * 锁的是什么？
     * 资源 -> state
     * 0 表示未加锁状态
     * >0 表示当前lock是加锁状态..
     */
    private volatile int state;

    /**
     * 独占模式？
     * 同一时刻只有一个线程可以持有锁，其它的线程，在未获取到锁时，会被阻塞..
     */
    //当前独占锁的线程（占用锁线程）
    private Thread exclusiveOwnerThread;


    /**
     * 需要有两个引用是维护 阻塞队列
     * 1.Head 指向队列的头节点
     * 2.Tail 指向队列的尾节点
     */
    //比较特殊：head节点对应的线程 就是当前占用锁的线程
    private Node head;
    private Node tail;





    /**
     * 阻塞的线程被封装成什么？？
     * Node节点，然后放入到FIFO队列
     */
    static final class Node {
        //前置节点引用
        Node prev;
        //后置节点引用
        Node next;
        //封装的线程本尊
        Thread thread;

        public Node(Thread thread) {
            this.thread = thread;
        }

        public Node() {
        }
    }


    /**
     * 获取锁
     * 假设当前锁被占用，则会阻塞调用者线程,直到抢占到锁为止
     * 模拟公平锁
     * 什么是公平锁？ 讲究一个先来后到！
     *
     * lock的过程是怎样的？
     * 情景1：线程进来后发现，当前state == 0 ，这个时候就很幸运 直接抢锁.
     * 情景2：线程进来后发现，当前state > 0 , 这个时候就需要将当前线程入队。
     */
    @Override
    public void lock() {
        //第一次获取到锁时：将state == 1
        //第n次重入时：将state == n
        acquire(1);
    }

    /**
     * 竞争资源
     * 1.尝试获取锁，成功则 占用锁 且返回..
     * 2.抢占锁失败，阻塞当前线程..
     */
    private void acquire(int arg) {
        if(!tryAcquire(arg)) {
            Node node = addWaiter();
            acquireQueued(node, arg);
        }
    }

    /**
     * 尝试抢占锁失败，需要做什么呢？
     * 1.需要将当前线程封装成 node，加入到 阻塞队列
     * 2.需要将当前线程park掉，使线程处于 挂起 状态
     *
     * 唤醒后呢？
     * 1.检查当前node节点 是否为 head.next 节点
     *   （head.next 节点是拥有抢占权限的线程.其他的node都没有抢占的权限。）
     * 2.抢占
     *   成功：1.将当前node设置为head，将老的head出队操作，返回到业务层面
     *   失败：2.继续park，等待被唤醒..
     *
     * ====>
     * 1.添加到 阻塞队列的逻辑  addWaiter()
     * 2.竞争资源的逻辑        acquireQueued()
     */


    private void acquireQueued(Node node, int arg) {
        //只有当前node成功获取到锁 以后 才会跳出自旋。
        for(;;) {
            //什么情况下，当前node被唤醒之后可以尝试去获取锁呢？
            //只有一种情况，当前node是 head 的后继节点。才有这个权限。


            //head 节点 就是 当前持锁节点..
            Node pred = node.prev;
            if(pred == head/*条件成立：说明当前node拥有抢占权限..*/ && tryAcquire(arg)) {
                //这里面，说明当前线程 竞争锁成功啦！
                //需要做点什么？
                //1.设置当前head 为当前线程的node
                //2.协助 原始 head 出队
                setHead(node);
                pred.next = null; // help GC
                return;
            }

            System.out.println("线程：" + Thread.currentThread().getName() + "，挂起！");
            //将当前线程挂起！
            LockSupport.park();
            System.out.println("线程：" + Thread.currentThread().getName() + "，唤醒！");

            //什么时候唤醒被park的线程呢？
            //unlock 过程了！
        }
    }

    /**
     * 当前线程入队
     * 返回当前线程对应的Node节点
     *
     * addWaiter方法执行完毕后，保证当前线程已经入队成功！
     */
    private Node addWaiter() {
        Node newNode = new Node(Thread.currentThread());
        //如何入队呢？
        //1.找到newNode的前置节点 pred
        //2.更新newNode.prev = pred
        //3.CAS 更新tail 为 newNode
        //4.更新 pred.next = newNode

        //前置条件：队列已经有等待者node了，当前node 不是第一个入队的node
        Node pred = tail;
        if(pred != null) {
            newNode.prev = pred;
            //条件成立：说明当前线程成功入队！
            if(compareAndSetTail(pred, newNode)) {
                pred.next = newNode;
                return newNode;
            }
        }

        //执行到这里的有几种情况？
        //1.tail == null 队列是空队列
        //2.cas 设置当前newNode 为 tail 时 失败了...被其它线程抢先一步了...
        enq(newNode);
        return newNode;
    }

    /**
     * 自旋入队，只有成功后才返回.
     *
     * 1.tail == null 队列是空队列
     * 2.cas 设置当前newNode 为 tail 时 失败了...被其它线程抢先一步了...
     */
    private void enq(Node node) {
        for(;;) {
            //第一种情况：队列是空队列
            //==> 当前线程是第一个抢占锁失败的线程..
            //当前持有锁的线程，并没有设置过任何 node,所以作为该线程的第一个后驱，需要给它擦屁股、
            //给当前持有锁的线程 补充一个 node 作为head节点。  head节点 任何时候，都代表当前占用锁的线程。
            if(tail == null) {
                //条件成立：说明当前线程 给 当前持有锁的线程 补充 head操作成功了..
                if(compareAndSetHead(new Node())) {
                    tail = head;
                    //注意：并没有直接返回，还会继续自旋...
                }
            } else {
                //说明：当前队列中已经有node了，这里是一个追加node的过程。

                //如何入队呢？
                //1.找到newNode的前置节点 pred
                //2.更新newNode.prev = pred
                //3.CAS 更新tail 为 newNode
                //4.更新 pred.next = newNode

                //前置条件：队列已经有等待者node了，当前node 不是第一个入队的node
                Node pred = tail;
                if(pred != null) {
                    node.prev = pred;
                    //条件成立：说明当前线程成功入队！
                    if(compareAndSetTail(pred, node)) {
                        pred.next = node;
                        //注意：入队成功之后，一定要return。。
                        return;
                    }
                }
            }
        }
    }




    /**
     * 尝试获取锁，不会阻塞线程
     * true -> 抢占成功
     * false -> 抢占失败
     */
    private boolean tryAcquire(int arg) {

        if(state == 0) {
            //当前state == 0 时，是否可以直接抢锁呢？
            //不可以，因为咱们模拟的是  公平锁，先来后到..
            //条件一：!hasQueuedPredecessor() 取反之后值为true 表示当前线程前面没有等待者线程。
            //条件二：compareAndSetState(0, arg)  为什么使用CAS ? 因为lock方法可能有多线程调用的情况..
            //      成立：说明当前线程抢锁成功
            if(!hasQueuedPredecessor() && compareAndSetState(0, arg)) {
                //抢锁成功了，需要干点啥？
                //1.需要将exclusiveOwnerThread 设置为 当前进入if块中的线程
                this.exclusiveOwnerThread = Thread.currentThread();
                return true;
            }
            //什么时候会执行 else if 条件？
            //当前锁是被占用的时候会来到这个条件这里..

            //条件成立：Thread.currentThread() == this.exclusiveOwnerThread
            //说明当前线程即为持锁线程，是需要返回true的！
            //并且更新state值。
        } else if(Thread.currentThread() == this.exclusiveOwnerThread) {
            //这里面存在并发么？ 不存在的 。 只有当前加锁的线程，才有权限修改state。
            //锁重入的流程
            //说明当前线程即为持锁线程，是需要返回true的！
            int c = getState();
            c = c + arg;
            //越界判断..
            this.state = c;
            return true;
        }

        //什么时候会返回false?
        //1.CAS加锁失败
        //2.state > 0 且 当前线程不是占用者线程。
        return false;
    }


    /**
     * true -> 表示当前线程前面有等待者线程
     * false -> 当前线程前面没有其它等待者线程。
     *
     * 调用链
     * lock -> acquire -> tryAcquire  -> hasQueuedPredecessor (ps：state值为0 时，即当前Lock属于无主状态..)
     *
     * 什么时候返回false呢？
     * 1.当前队列是空..
     * 2.当前线程为head.next节点 线程.. head.next在任何时候都有权利去争取一下lock
     */
    private boolean hasQueuedPredecessor() {
        Node h = head;
        Node t = tail;
        Node s;

        //条件一：h != t
        //成立：说明当前队列已经有node了...
        //不成立：1. h == t == null   2. h == t == head 第一个获取锁失败的线程，会为当前持有锁的线程 补充创建一个 head 节点。


        //条件二：前置条件：条件一成立 ((s = h.next) == null || s.thread != Thread.currentThread())
        //排除几种情况
        //条件2.1：(s = h.next) == null
        //极端情况：第一个获取锁失败的线程，会为 持锁线程 补充创建 head ，然后再自旋入队，  1. cas tail() 成功了，2. pred【head】.next = node;
        //其实想表达的就是：已经有head.next节点了，其它线程再来这时  需要返回 true。

        //条件2.2：前置条件,h.next 不是空。 s.thread != Thread.currentThread()
        //条件成立：说明当前线程，就不是h.next节点对应的线程...返回true。
        //条件不成立：说明当前线程，就是h.next节点对应的线程，需要返回false，回头线程会去竞争锁了。
        return h != t && ((s = h.next) == null || s.thread != Thread.currentThread());
    }




    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        release(1);
    }

    private void release(int arg) {
        //条件成立：说明线程已经完全释放锁了
        //需要干点啥呢？
        //阻塞队列里面还有好几个睡觉的线程呢？ 是不是 应该喊醒一个线程呢？
        if(tryRelease(arg)) {
            Node head = this.head;

            //你得知道，有没有等待者？   就是判断 head.next == null  说明没有等待者， head.next != null 说明有等待者..
            if(head.next != null) {
                //公平锁，就是唤醒head.next节点
                unparkSuccessor(head);
            }
        }
    }

    private void unparkSuccessor(Node node) {
        Node s = node.next;
        if(s != null && s.thread != null) {
            LockSupport.unpark(s.thread);
        }
    }

    /**
     * 完全释放锁成功，则返回true
     * 否则，说明当前state > 0 ，返回false.
     */
    private boolean tryRelease(int arg) {
        int c = getState() - arg;

        if(getExclusiveOwnerThread() != Thread.currentThread()) {
            throw new RuntimeException("fuck you! must getLock!");
        }
        //如果执行到这里？存在并发么？ 只有一个线程 ExclusiveOwnerThread 会来到这里。

        //条件成立：说明当前线程持有的lock锁 已经完全释放了..
        if(c == 0) {
            //需要做什么呢？
            //1.ExclusiveOwnerThread 置为null
            //2.设置 state == 0
            this.exclusiveOwnerThread = null;
            this.state = c;
            return true;
        }

        this.state = c;
        return false;
    }


    private void setHead(Node node) {
        this.head = node;
        //为什么？ 因为当前node已经是获取锁成功的线程了...
        node.thread = null;
        node.prev = null;
    }

    public int getState() {
        return state;
    }

    public Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    private static final Unsafe unsafe;
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);

            stateOffset = unsafe.objectFieldOffset
                    (MiniReentrantLock.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (MiniReentrantLock.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (MiniReentrantLock.class.getDeclaredField("tail"));

        } catch (Exception ex) { throw new Error(ex); }
    }

    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }
}
