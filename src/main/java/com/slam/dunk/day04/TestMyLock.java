package com.slam.dunk.day04;

import com.slam.dunk.day04.aqs.TrinityLock;
import com.slam.dunk.utils.SleepTools;

import java.util.concurrent.locks.Lock;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 18:03
 * @Description:
 */
public class TestMyLock {

    //Lock lock = new ReentrantLock();
    //Lock lock = new MyLock();
    Lock lock = new TrinityLock();

    class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    SleepTools.second(1);
                    System.out.println(Thread.currentThread().getName());
                    SleepTools.second(1);
                } finally {
                    lock.unlock();
                }
                SleepTools.second(2);
            }
        }
    }

    public void test() {
        // start 10 threads
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        //main thread print line every second
        for (int i = 0; i < 10; i++) {
            SleepTools.second(1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }
}
