package com.slam.dunk.day06.mypool;

import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 09:39
 * @Description:
 */
public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        // create a thread pool
        MyThreadPool t = new MyThreadPool(3, 0);
        t.execute(new MyTask("testA"));
        t.execute(new MyTask("testB"));
        t.execute(new MyTask("testC"));
        t.execute(new MyTask("testD"));
        t.execute(new MyTask("testE"));
        System.out.println(t);
        Thread.sleep(10000);
        //destroy thread pool
        t.destroy();
        System.out.println(t);
    }

    /**
     * task class
     */
    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(1000) + 2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId() + " sleep InterruptedException:"
                        + Thread.currentThread().isInterrupted());
            }
            System.out.println("task-- " + name + " finish");
        }
    }
}
