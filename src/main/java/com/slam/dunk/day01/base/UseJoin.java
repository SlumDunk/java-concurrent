package com.slam.dunk.day01.base;

import com.slam.dunk.utils.SleepTools;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 09:52
 * @Description:
 */
public class UseJoin {
    /**
     * jump in a queue
     */
    static class JumpQueue implements Runnable {
        /**
         * previous thread that current thread need to wait
         */
        private Thread thread;

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                System.out.println(thread.getName() + " will be join before "
                        + Thread.currentThread().getName());
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminted.");
        }
    }

    public static void main(String[] args) throws Exception {
        //at begin, previous is the current thread
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            //i=0,previous is main thread，i=1;previous is the the thread created when i=0
            Thread thread =
                    new Thread(new JumpQueue(previous), String.valueOf(i));
            System.out.println(previous.getName() + " jump a queue the thread:"
                    + thread.getName());
            thread.start();
            System.out.println();
            previous = thread;
        }

        //main thread sleep 2 ms
        SleepTools.second(2);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
}
