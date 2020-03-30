package com.slam.dunk.day02.future;

import com.slam.dunk.utils.SleepTools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 10:39
 * @Description:
 */
public class UseFuture {
    /**
     * implements Callable Interface
     */
    private static class UseCallable implements Callable<Integer> {

        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable child thread start to work");
            Thread.sleep(2000);
            for (int i = 0; i < 5000; i++) {
                sum = sum + i;
            }
            System.out.println("Callable child thread end work, the sum=" + sum);
            return sum;
        }

    }

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        UseCallable useCallable = new UseCallable();
        //encapsulate the callable instance as the future task
        FutureTask<Integer> futureTask = new FutureTask<Integer>(useCallable);
        new Thread(futureTask).start();
        Random r = new Random();
        SleepTools.second(1);
        //decide whether get the result or terminate the task
        if (r.nextBoolean()) {
            System.out.println("Get UseCallable result = " + futureTask.get());
        } else {
            System.out.println("terminate computing");
            //we can cancel the task during it is running
            futureTask.cancel(true);
        }

    }
}
