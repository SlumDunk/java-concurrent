package com.slam.dunk.day06.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 10:58
 * @Description:
 */
public class ScheduleWorker implements Runnable {
    /**
     * normal task
     */
    public final static int Normal = 0;
    /**
     * task that will throw exception
     */
    public final static int HasException = -1;
    /**
     * task that will throw exception and catch exception
     */
    public final static int ProcessException = 1;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private int taskType;

    public ScheduleWorker(int taskType) {
        this.taskType = taskType;
    }

    @Override
    public void run() {
        if (taskType == HasException) {
            System.out.println(dateFormat.format(new Date()) + " Exception made...");
            throw new RuntimeException("HasException Happen");
        } else if (taskType == ProcessException) {
            try {
                System.out.println(dateFormat.format(new Date())
                        + " Exception made,but catch");
                throw new RuntimeException("HasException Happen");
            } catch (Exception e) {
                System.out.println(" Exception is caught");
            }
        } else {
            System.out.println(" Normal ...." + dateFormat.format(new Date()));
        }
    }
}
