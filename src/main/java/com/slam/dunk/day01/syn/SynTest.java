package com.slam.dunk.day01.syn;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 23:09
 * @Description:
 *
 */
public class SynTest {


    /**
     * volatile key word, every time when you read the variable, load it from main memory
     * every time when you write the variable, flush the variable into main memory
     */
    private volatile int age = 100000;

    public void setAge() {
        age = age + 20;
    }

    private static class TestThread extends Thread {

        private SynTest synTest;

        public TestThread(SynTest synTest, String name) {
            super(name);
            this.synTest = synTest;
        }

        @Override
        public void run() {
            //add
            for (int i = 0; i < 100000; i++) {
                synTest.test();
            }
            System.out.println(Thread.currentThread().getName()
                    + " age =  " + synTest.getAge());
        }
    }

    public synchronized void test() {
        age++;
        test2();
    }

    public synchronized void test2() {
        {
            age--;
        }
    }

    public int getAge() {
        return age;
    }


    public static void main(String[] args) throws InterruptedException {
        SynTest synTest = new SynTest();
        Thread endThread = new TestThread(synTest, "endThread");
        endThread.start();
        //minus
        for (int i = 0; i < 100000; i++) {
            synTest.test2();
        }
        System.out.println(Thread.currentThread().getName()
                + " age =  " + synTest.getAge());

    }
}
