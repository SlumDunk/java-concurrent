package com.slam.dunk.day09.jmm;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 12:40
 * @Description:
 */
public class VolaLikeSyn {
    long i = 0L;

    /**
     * mock read of volatile variable
     *
     * @return
     */
    public synchronized long getI() {
        return i;
    }

    /**
     * mock write
     *
     * @param i
     */
    public synchronized void setI(long i) {
        this.i = i;
    }

    /**
     * mock increment
     */
    public void inc() {
        long temp = getI();
        temp = temp + 1L;
        setI(temp);
    }
}
