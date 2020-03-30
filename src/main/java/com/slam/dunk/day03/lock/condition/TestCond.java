package com.slam.dunk.day03.lock.condition;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:49
 * @Description:
 */
public class TestCond {
    public static final int THREAD_NUM = 3;
    private static ExpressCond express = new ExpressCond(0, ExpressCond.CITY);

    /**
     * thread that checks the km condition
     */
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /**
     * thread that checks the site condition
     */
    private static class CheckSite extends Thread {
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_NUM; i++) {
            new CheckSite().start();
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            new CheckKm().start();
        }

        Thread.sleep(1000);
        //update km
        express.changeKm();
    }
}
