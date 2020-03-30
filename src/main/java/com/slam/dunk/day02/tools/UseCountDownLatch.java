package com.slam.dunk.day02.tools;

import com.slam.dunk.utils.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 10:44
 * @Description:
 */
public class UseCountDownLatch {

    static CountDownLatch latch = new CountDownLatch(6);

    /**
     * the thread that spends time to initiate the work
     */
    private static class InitRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread_" + Thread.currentThread().getId()
                    + " ready init work......");
            //after finish initiation work
            latch.countDown();
            //can do other work
            for (int i = 0; i < 2; i++) {
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ........continue do its work");
            }
        }
    }

    /**
     * business thread
     */
    private static class BusinessRunnable implements Runnable {

        @Override
        public void run() {
            try {
                //waiting  for the completion of other threads' initiation
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("BusinessThread_" + Thread.currentThread().getId()
                        + " do business-----");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //have 2 steps to initiate the work
        new Thread(new Runnable() {
            @Override
            public void run() {
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ready init work step 1st......");
                //count down every time you finish one step
                latch.countDown();
                System.out.println("begin step 2nd.......");
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ready init work step 2nd......");
                //count down every time you finish one step
                latch.countDown();
            }
        }).start();

        new Thread(new BusinessRunnable()).start();

        //4 other threads
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new InitRunnable());
            thread.start();
        }

        latch.await();
        System.out.println("Main do its work........");
    }
}
