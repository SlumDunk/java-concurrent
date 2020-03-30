package com.slam.dunk.day07.safeclass;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:17
 * @Description: has no field attributes, thread safe
 */
public class StatelessClass {
    public int service(int a, int b) {
        return a * b;
    }
}
