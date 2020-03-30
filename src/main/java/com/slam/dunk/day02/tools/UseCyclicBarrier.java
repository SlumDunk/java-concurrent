package com.slam.dunk.day02.tools;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 10:59
 * @Description:
 */
public class UseCyclicBarrier {

    /**
     * participate parties, the other one is the function that be triggered when all parties are ready
     */
    private static CyclicBarrier barrier
            = new CyclicBarrier(5, new CollectThread());

    /**
     * store the result of the child-threads
     */
    private static ConcurrentHashMap<String, Long> resultMap
            = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i <= 4; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }

    }

    /**
     * what to do when the barrier is open
     */
    private static class CollectThread implements Runnable {

        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
                result.append("[" + workResult.getValue() + "]");
            }
            System.out.println(" the result = " + result);
            System.out.println("do other business........");
        }
    }

    /**
     * work thread
     */
    private static class SubThread implements Runnable {

        @Override
        public void run() {
            //result of each thread
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "", id);
            //decide whether sleep or not randomly
            Random r = new Random();
            try {
                if (r.nextBoolean()) {
                    Thread.sleep(2000 + id);
                    System.out.println("Thread_" + id + " ....do something ");
                }
                System.out.println(id + "....is await");
                barrier.await();
                //all the threads are waiting for the signal to do the rest work at the same time
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + " ....do its business ");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
