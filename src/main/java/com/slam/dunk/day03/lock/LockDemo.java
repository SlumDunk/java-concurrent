package com.slam.dunk.day03.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:20
 * @Description:
 */
public class LockDemo {
    private Lock lock = new ReentrantLock();
    private int count;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * reentrant lock, just like this
     */
    public synchronized void incr2() {
        count++;
        //recursion, reentrant, lock counter
        incr2();
    }

    public synchronized void test3() {
        //reentrant
        incr2();
    }

}
