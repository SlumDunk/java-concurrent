package com.slam.dunk.day02.forkjoin;

import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 10:15
 * @Description:
 */
public class MakeArray {
    /**
     * length of the array
     */
    public static final int ARRAY_LENGTH = 100000000;

    public static int[] makeArray() {

        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            //get the random number
            result[i] = r.nextInt(ARRAY_LENGTH * 3);
        }
        return result;

    }
}
