package com.slam.dunk.day06.mypool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 09:33
 * @Description:
 */
public class MyThreadPool {
    /**
     * default worker thread number
     */
    private static int DEFAULT_WORKER_NUM = 5;
    /**
     * default size of task queue
     */
    private static int DEFAULT_QUEUE_SIZE = 100;

    /**
     * work thread group
     */
    private WorkThread[] workThreads;

    /**
     * task queue
     */
    private final BlockingQueue<Runnable> taskQueue;

    /**
     * real worker number
     */
    private final int worker_num;

    public MyThreadPool() {
        this(DEFAULT_WORKER_NUM, DEFAULT_QUEUE_SIZE);
    }

    public MyThreadPool(int worker_num, int queueSize) {
        if (worker_num <= 0) {
            worker_num = DEFAULT_WORKER_NUM;
        }
        if (queueSize <= 0) {
            queueSize = DEFAULT_QUEUE_SIZE;
        }
        this.worker_num = worker_num;
        taskQueue = new ArrayBlockingQueue<>(queueSize);
        workThreads = new WorkThread[worker_num];
        for (int i = 0; i < worker_num; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
        Runtime.getRuntime().availableProcessors();
    }


    /**
     * submit execute task, just put into queue, let the thread pool finish it automatically
     *
     * @param task
     */
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * destroy all the threads in the pool after they finish their task
     */
    public void destroy() {
        System.out.println("ready close pool.....");
        for (int i = 0; i < worker_num; i++) {
            workThreads[i].stopWorker();
            //help gc
            workThreads[i] = null;
        }
        //clear task queue
        taskQueue.clear();
    }

    /**
     * return the pool status
     *
     * @return
     */
    @Override
    public String toString() {
        return "WorkThread number:" + worker_num
                + "  wait task number:" + taskQueue.size();
    }

    /**
     * worker thread
     */
    private class WorkThread extends Thread {

        @Override
        public void run() {
            Runnable r = null;
            try {
                while (!isInterrupted()) {
                    r = taskQueue.take();
                    if (r != null) {
                        System.out.println(getId() + " ready exec :" + r);
                        r.run();
                    }
                    //help gc
                    r = null;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        public void stopWorker() {
            interrupt();
        }

    }
}
