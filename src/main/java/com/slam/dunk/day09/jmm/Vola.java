package com.slam.dunk.day09.jmm;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 12:39
 * @Description:
 */
public class Vola {
    /**
     * volatile variable
     */
    volatile long i = 0L;

    /**
     * read
     *
     * @return
     */
    public long getI() {
        return i;
    }

    /**
     * write
     *
     * @param i
     */
    public void setI(long i) {
        this.i = i;
    }

    /**
     * several instructions
     */
    public void inc() {
        i++;
    }
}
