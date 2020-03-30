package com.slam.dunk.day09.jmm;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 12:40
 * @Description:
 */
public class VolaSemantic {
    int a = 0;
    volatile boolean flag = false;

    public void init() {
        a = 1;
        flag = true;
        //.......
    }

    public void use() {
        if (flag) {
            int i = a * a;
        }
        //.......
    }
}
