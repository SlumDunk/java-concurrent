package com.slam.dunk.day09;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 12:35
 * @Description:
 */
public class ControlDep {
    int a = 0;
    boolean flag = false;

    public void init() {
        //1
        a = 1;
        //2
        flag = true;
        //.......
    }

    public void use() {
        //3
        if (flag) {
            // 4
            int i = a * a;
        }
        //.......
    }
}
