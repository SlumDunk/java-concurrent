package com.slam.dunk.day01.safestop;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 22:31
 * @Description:
 */
public class EndRunnable {
    private static class UseRunnable implements Runnable {

        @Override
        public void run() {

            String threadName = Thread.currentThread().getName();
            while (Thread.currentThread().isInterrupted()) {
                System.out.println(threadName + " is run!");
            }
            System.out.println(threadName + " interrput flag is "
                    + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread endThread = new Thread(useRunnable, "endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
