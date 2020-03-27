package com.slam.dunk.day01.safestop;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 22:25
 * @Description: how to interrupt a thread safely
 */
public class EndThread {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
//            while (!isInterrupted()) {
//                System.out.println(threadName + " is run!");
//            }
//
//            System.out.println(threadName + " interrupt flag is " + isInterrupted());

            //the thread won't stop if we don't deal with the interruption
            while (true) {
                System.out.println(threadName + " is run!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
