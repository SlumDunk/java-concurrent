package com.slam.dunk.day01.safestop;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 22:36
 * @Description:
 */
public class HasInterruptException {
    private static SimpleDateFormat formater
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss_SSS");

    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
                try {
                    System.out.println("UseThread:" + formater.format(new Date()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " catch interrput flag is "
                            + isInterrupted() + " at "
                            + (formater.format(new Date())));
                    // if any thread has interrupted the current thread. The
                    // <i>interrupted status</i> of the current thread is
                    // cleared when this exception is thrown.
                    // reset the interrupted status manually
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(threadName);
            }
            System.out.println(threadName + " interrput flag is "
                    + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("HasInterrputEx");
        endThread.start();
        System.out.println("Main:" + formater.format(new Date()));
        Thread.sleep(800);
        System.out.println("Main begin interrupt thread:" + formater.format(new Date()));
        endThread.interrupt();
    }
}
