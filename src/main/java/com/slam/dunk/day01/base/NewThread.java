package com.slam.dunk.day01.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 21:59
 * @Description: how to create new thread
 * <p>
 * threads cooperate with each other,协作式，不是强制式
 */
public class NewThread {

    /**
     * extends Thread class
     */
    private static class UseThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("I extend Thread");
        }
    }


    /**
     * implements Runnable interface
     */
    private static class UseRun implements Runnable {

        @Override
        public void run() {
            System.out.println("I implement Runnable Interface");
        }
    }

    /**
     * implements Callable interface, have return val
     */
    private static class UseCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("I implement Callable Interface");
            return "call Result";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseThread useThread = new UseThread();
        useThread.start();

        UseRun useRun = new UseRun();
        new Thread(useRun).start();

        //协作式 cooperate with each other
        Thread t = new Thread(useRun);
        t.interrupt();

        UseCall useCall = new UseCall();
        FutureTask<String> futureTask = new FutureTask<String>(useCall);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
