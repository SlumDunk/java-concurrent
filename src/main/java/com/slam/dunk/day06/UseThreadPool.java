package com.slam.dunk.day06;

import com.slam.dunk.utils.SleepTools;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 09:44
 * @Description:
 */
public class UseThreadPool {
    /**
     * worker thread
     */
    static class Worker implements Runnable {
        private String taskName;
        private Random r = new Random();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + " process the task : " + taskName);
            SleepTools.ms(r.nextInt(100) * 5);
        }
    }

    /**
     * Implements Callable
     */
    static class CallWorker implements Callable<String> {

        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()
                    + " process the task : " + taskName);
            return Thread.currentThread().getName() + ":" + r.nextInt(100) * 5;
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
//        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            Worker worker = new Worker("worker_" + i);
            pool.execute(worker);
        }
        for (int i = 0; i < 6; i++) {
            CallWorker callWorker = new CallWorker("callWorker_" + i);
            Future<String> result = pool.submit(callWorker);
            System.out.println(result.get());
        }
        pool.shutdown();
    }
}
