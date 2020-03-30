package com.slam.dunk.day03.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 15:18
 * @Description:
 */
public class AtomicArray {
    static int[] value = new int[]{1, 2};

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);

    }
}
