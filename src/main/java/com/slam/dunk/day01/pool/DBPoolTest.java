package com.slam.dunk.day01.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 09:32
 * @Description:
 */
public class DBPoolTest {
    static DBPool pool = new DBPool(10);
    /**
     * coordinate the main thread and child-threads
     */
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // number of threads
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        //operation counts of each thread
        int count = 20;
        //count the number of threads that get connection
        AtomicInteger got = new AtomicInteger();
        //count the number of threads that doesn't get connection
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notGot),
                    "worker_" + i);
            thread.start();
        }
        // main thread waits for the child-threads
        end.await();
        System.out.println("total: " + (threadCount * count));
        System.out.println("get connection：  " + got);
        System.out.println("doesn't get connection： " + notGot);
    }

    /**
     * worker
     */
    static class Worker implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got,
                      AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    // get connection from db pool, timeout is 1000 ms
                    Connection connection = pool.fetchConn(1000);
                    if (connection != null) {
                        try {
                            //do some work
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConn(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName()
                                + "wait timeout!");
                    }
                } catch (Exception ex) {
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
