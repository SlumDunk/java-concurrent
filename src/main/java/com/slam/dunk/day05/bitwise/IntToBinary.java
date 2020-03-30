package com.slam.dunk.day05.bitwise;

import java.io.UnsupportedEncodingException;

/**
 * @Author: zerongliu
 * @Date: 3/28/20 11:46
 * @Description:
 */
public class IntToBinary {
    public static void main(String[] args) throws UnsupportedEncodingException {

        int data = 4;
        System.out.println("the 4 is " + Integer.toBinaryString(data));

        //&(1&1=1 1&0=0 0&0=0)
        System.out.println("the 4 is " + Integer.toBinaryString(4));
        System.out.println("the 6 is " + Integer.toBinaryString(6));
        System.out.println("the 4&6 is " + Integer.toBinaryString(4 & 6));
        //| (1|1=1 1|0=1 0|0=0)
        System.out.println("the 4|6 is " + Integer.toBinaryString(4 | 6));
        //~（~1=0  ~0=1）
        System.out.println("the ~4 is " + Integer.toBinaryString(~4));
        //^ (1^1=0 1^0=1 0^0=0)
        System.out.println("the 4^6 is " + Integer.toBinaryString(4 ^ 6));

        // <<Signed left shift >>Signed right shift  >>>non-Signed right shift

        //a % (2^n) = a&(2^n-1)
        System.out.println("the 345 % 16 is " + (345 % 16) + " or " + (345 & (16 - 1)));

    }
}
