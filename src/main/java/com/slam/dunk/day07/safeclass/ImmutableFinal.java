package com.slam.dunk.day07.safeclass;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:15
 * @Description:
 */
public class ImmutableFinal {
    private final int a;
    private final int b;

    public ImmutableFinal(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
