package com.slam.dunk.day06.completion;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 11:17
 * @Description:
 */
public class WorkTask implements Callable<Integer> {
    private String name;

    public WorkTask(String name) {
        this.name = name;
    }

    @Override
    public Integer call() {
        int sleepTime = new Random().nextInt(1000);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // return result
        return sleepTime;
    }
}
