package com.slam.dunk.day01.base;

import java.util.concurrent.ExecutionException;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 23:02
 * @Description: don't put the clean task in the daemon thread if you need to clean the resources when you stop the thread
 */
public class DaemonThread {
    private static class UseThread extends Thread {
        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName()
                            + " I extend Thread.");
                }
                System.out.println(Thread.currentThread().getName()
                        + " interrupt flag is " + isInterrupted());
            } finally {// this block may not work in the daemon thread
                System.out.println("...........finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        UseThread useThread = new UseThread();
        //the lifecycle of the useThread is same as the main thread
//        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);
        useThread.interrupt();
    }
}
