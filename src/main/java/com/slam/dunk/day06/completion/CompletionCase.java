package com.slam.dunk.day06.completion;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 11:16
 * @Description:
 */
public class CompletionCase {
    private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final int TOTAL_TASK = Runtime.getRuntime().availableProcessors() * 100;

    /**
     * use blocking queue to store the future result
     *
     * @throws Exception
     */
    public void testByQueue() throws Exception {
        long start = System.currentTimeMillis();
        //calculate the execution time
        AtomicInteger count = new AtomicInteger(0);
        // create thread pool
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        //store the future result
        BlockingQueue<Future<Integer>> queue =
                new LinkedBlockingQueue<Future<Integer>>();

        // submit task
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<Integer> future = pool.submit(new WorkTask("ExecTask" + i));
            queue.add(future);
        }

        //check result, first submit, first return
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleptTime = queue.take().get();
            System.out.println(" slept " + sleptTime + " ms ...");
            count.addAndGet(sleptTime);
        }

        // close thread pool
        pool.shutdown();
        System.out.println("-------------tasks sleep time " + count.get()
                + "ms,and spend time "
                + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * use CompletionService to get the result
     *
     * @throws Exception
     */
    public void testByCompletion() throws Exception {
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        // create thread pool
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        //decoration module
        CompletionService<Integer> cService = new ExecutorCompletionService<>(pool);

        // submit task
        for (int i = 0; i < TOTAL_TASK; i++) {
            cService.submit(new WorkTask("ExecTask" + i));
        }

        // get result, first finish, first return
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleptTime = cService.take().get();
            System.out.println(" slept " + sleptTime + " ms ...");
            count.addAndGet(sleptTime);
        }

        // shutdown thread pool
        pool.shutdown();
        System.out.println("-------------tasks sleep time " + count.get()
                + "ms,and spend time "
                + (System.currentTimeMillis() - start) + " ms");
    }

    public static void main(String[] args) throws Exception {
        CompletionCase t = new CompletionCase();
        t.testByQueue();
        t.testByCompletion();
    }
}
