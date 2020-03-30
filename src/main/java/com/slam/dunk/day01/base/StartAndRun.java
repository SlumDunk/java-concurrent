package com.slam.dunk.day01.base;

import com.slam.dunk.utils.SleepTools;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 22:54
 * @Description: difference of method run() and start()
 */
public class StartAndRun {
    public static class ThreadRun extends Thread {

        @Override
        public void run() {
            int i = 90;
            while (i > 0) {
                SleepTools.ms(1000);
                System.out.println("I am " + Thread.currentThread().getName()
                        + " and now the i=" + i--);
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun beCalled = new ThreadRun();
        beCalled.setName("BeCalled");
        beCalled.setPriority(10);
        //run as a normal method of an object in main thread
        //beCalled.run();
        //start a new thread
        beCalled.start();
    }
}
