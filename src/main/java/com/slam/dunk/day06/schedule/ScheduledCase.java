package com.slam.dunk.day06.schedule;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 10:57
 * @Description:
 */
public class ScheduledCase {
    public static void main(String[] args) {

        ScheduledThreadPoolExecutor schedule
                = new ScheduledThreadPoolExecutor(1);

        //if we don't handle the exception in the Runnable task, the thread pool will not continue to work in the next cycle
        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.HasException),
                1000, 3000, TimeUnit.MILLISECONDS);
        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.Normal),
                1000, 3000, TimeUnit.MILLISECONDS);

    }
}
