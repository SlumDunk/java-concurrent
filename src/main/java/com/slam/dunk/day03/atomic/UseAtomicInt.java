package com.slam.dunk.day03.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 15:16
 * @Description:
 */
public class UseAtomicInt {
    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        //10--->11
        System.out.println(ai.getAndIncrement());
        //11--->12--->out
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.get());
    }
}
