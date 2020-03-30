package com.slam.dunk.day08.demo01.service;

import com.slam.dunk.day05.blockqueue.ItemVo;
import com.slam.dunk.day08.demo01.pool.PendingJobPool;

import java.util.concurrent.DelayQueue;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:09
 * @Description: remove the job has been done from the job pool
 */
public class CheckJobProcessor {
    /**
     * store the tasks have been done, wait for expiration
     */
    private static DelayQueue<ItemVo<String>> queue
            = new DelayQueue<ItemVo<String>>();

    /**
     * single instance
     */
    private CheckJobProcessor() {
    }

    private static class ProcessorHolder {
        public static CheckJobProcessor processor = new CheckJobProcessor();
    }

    public static CheckJobProcessor getInstance() {
        return ProcessorHolder.processor;
    }

    /**
     * implements the business logic to remove the result from pool
     */
    private static class FetchJob implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    //get the job that is expired
                    ItemVo<String> item = queue.take();
                    String jobName = (String) item.getData();
                    PendingJobPool.getMap().remove(jobName);
                    System.out.println(jobName + " is out of date,remove from map!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * put the job is done into the queue, and set the expire time
     * @param jobName
     * @param expireTime
     */
    public void putJob(String jobName, long expireTime) {
        ItemVo<String> item = new ItemVo<String>(expireTime, jobName);
        queue.offer(item);
        System.out.println("Job[" + jobName + "has been put into the cache queue, the expire time is" + expireTime);
    }

    static {
        Thread thread = new Thread(new FetchJob());
        thread.setDaemon(true);
        thread.start();
        System.out.println("start the check processor thread................");
    }

}
