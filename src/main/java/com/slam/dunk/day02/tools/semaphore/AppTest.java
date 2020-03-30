package com.slam.dunk.day02.tools.semaphore;

import com.slam.dunk.utils.SleepTools;

import java.sql.Connection;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 15:00
 * @Description:
 */
public class AppTest {
    /**
     * db connection pool
     */
    private static DBPoolSemaphore dbPool = new DBPoolSemaphore();

    private static int THREAD_NUMBER = 50;


    private static CyclicBarrier barrier = new CyclicBarrier(THREAD_NUMBER);

    /**
     * business thread
     */
    private static class BusinessThread extends Thread {
        @Override
        public void run() {
            //random sleep
            Random r = new Random();
            long start = System.currentTimeMillis();
            try {
                barrier.await();
                Connection connect = dbPool.takeConnect();
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + "_cost [" + (System.currentTimeMillis() - start) + "] ms to get the db connection");
                //simulate the business work, query data
                SleepTools.ms(100 + r.nextInt(100));
                System.out.println("return connection after querying data!");
                dbPool.returnConnect(connect);
            } catch (InterruptedException | BrokenBarrierException e) {
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread = new BusinessThread();
            thread.start();
        }
    }
}
