package com.slam.dunk.day01.base;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 23:39
 * @Description:
 */
public class UseThreadLocal {
    /**
     * just as a map, Map<Thread,Integer>
     */
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    /**
     * create 3 threads
     */
    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestThread(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    /**
     * get the value from thread local and save the value into thread local
     */
    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            // get the value of the variable from thread local
            Integer val = threadLocal.get();
            val = val + id;
            threadLocal.set(val);
            System.out.println(Thread.currentThread().getName() + ":"
                    + threadLocal.get());
            threadLocal.remove();
        }
    }

    public static void main(String[] args) {
        UseThreadLocal test = new UseThreadLocal();
        test.StartThreadArray();
    }
}
