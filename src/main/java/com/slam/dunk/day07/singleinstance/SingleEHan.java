package com.slam.dunk.day07.singleinstance;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 19:43
 * @Description:
 */
public class SingleEHan {
    /**
     * JVM guarantee its thread safe
     */
    public static SingleEHan singleEHan = new SingleEHan();

    private SingleEHan() {
    }
}
