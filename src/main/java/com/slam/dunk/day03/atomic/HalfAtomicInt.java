package com.slam.dunk.day03.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 15:17
 * @Description:
 */
public class HalfAtomicInt {
    /**
     * CAS implements by hardware
     */
    private AtomicInteger atomicI = new AtomicInteger(0);

    /**
     * thread safe
     */
    public void increment() {
        //spin lock
        for (; ; ) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * get value
     *
     * @return
     */
    public int getCount() {
        return atomicI.get();
    }

}
