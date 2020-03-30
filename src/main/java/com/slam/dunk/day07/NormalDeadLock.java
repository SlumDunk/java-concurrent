package com.slam.dunk.day07;

import com.slam.dunk.utils.SleepTools;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:41
 * @Description:
 */
public class NormalDeadLock {
    /**
     * first lock
     */
    private static Object valueFirst = new Object();
    /**
     * second lock
     */
    private static Object valueSecond = new Object();

    /**
     * get 1st lock, then get 2nd lock
     *
     * @throws InterruptedException
     */
    private static void fisrttosecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueFirst) {
            System.out.println(threadName + " get first");
            SleepTools.ms(100);
            synchronized (valueSecond) {
                System.out.println(threadName + " get second");
            }
        }
    }

    /**
     * get 2nd lock, then get 1st lock
     *
     * @throws InterruptedException
     */
    private static void SecondToFisrt() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueSecond) {
            System.out.println(threadName + " get first");
            SleepTools.ms(100);
            synchronized (valueFirst) {
                System.out.println(threadName + " get second");
            }
        }
    }

    /**
     * require 2nd lock then 1st lock
     */
    private static class TestThread extends Thread {

        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                SecondToFisrt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("TestDeadLock");
        TestThread testThread = new TestThread("SubTestThread");
        testThread.start();
        try {
            fisrttosecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
