package com.slam.dunk.day08.demo01;

import com.slam.dunk.day08.demo01.pool.PendingJobPool;
import com.slam.dunk.day08.demo01.service.MyTaskProcessor;
import com.slam.dunk.day08.demo01.vo.TaskResult;
import com.slam.dunk.utils.SleepTools;

import java.util.List;
import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:14
 * @Description:
 */
public class AppTest {
    private final static String JOB_NAME = "calculate_number";
    private final static int JOB_LENGTH = 1000;

    /**
     * query the result
     */
    private static class QueryResult implements Runnable {

        private PendingJobPool pool;

        public QueryResult(PendingJobPool pool) {
            super();
            this.pool = pool;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 350) {
                List<TaskResult<String>> taskDetail = pool.getTaskDetail(JOB_NAME);
                if (!taskDetail.isEmpty()) {
                    System.out.println(pool.getTaskProgress(JOB_NAME));
                    System.out.println(taskDetail);
                }
                SleepTools.ms(100);
                i++;
            }
        }

    }

    public static void main(String[] args) {
        MyTaskProcessor myTask = new MyTaskProcessor();
        //get the instance
        PendingJobPool pool = PendingJobPool.getInstance();
        //register job
        pool.registerJob(JOB_NAME, JOB_LENGTH, myTask, 1000 * 5);
        Random r = new Random();
        for (int i = 0; i < JOB_LENGTH; i++) {
            //add task for this job
            pool.putTask(JOB_NAME, r.nextInt(1000));
        }
        Thread t = new Thread(new QueryResult(pool));
        t.start();
    }
}
