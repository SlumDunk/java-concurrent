package com.slam.dunk.day01.wn;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 09:21
 * @Description: test wait/notify, notifyAll
 */
public class TestWN {
    private static Express express = new Express(0, Express.CITY);

    /**
     * threads that check distance
     */
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /**
     * thread that checks site
     */
    private static class CheckSite extends Thread {
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //create three threads that check the change of site
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        //create three threads that check the change of distance
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }

        //give enough time for all threads to run
        Thread.sleep(1000);
        //change the distance
        express.changeKm();
    }
}
