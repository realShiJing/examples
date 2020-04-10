package com.nchu.threadlocal;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/1/10 17:08
 **/
public class ThreadLocalDemo {
    static class A implements Runnable {
        private ThreadLocal<String> threadLocal;

        public A(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA输出：" + threadLocal.get());
        }

        static class B implements Runnable {
            private ThreadLocal<String> threadLocal;

            public B(ThreadLocal<String> threadLocal) {
                this.threadLocal = threadLocal;
            }

            @Override
            public void run() {
                threadLocal.set("B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadB输出：" + threadLocal.get());
            }
        }

        public static void main(String[] args) throws InterruptedException {
            ThreadLocal<String> threadLocal = new ThreadLocal<>();
            Thread threadA = new Thread(new A(threadLocal));
            Thread threadB = new Thread(new B(threadLocal));

            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
            System.out.println(threadLocal.get());
        }
    }
}
