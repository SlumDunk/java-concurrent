package com.slam.dunk.day07.singleinstance;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 19:37
 * @Description: single instance double check
 */
public class SingleDcl {

    /**
     * solve the security, because some fields may not be initialized successfully
     */
    private volatile static SingleDcl singleDcl;

    private SingleDcl() {
    }

    public static SingleDcl getInstance() {
        if (singleDcl == null) {
            //class lock
            synchronized (SingleDcl.class) {
                if (singleDcl == null) {
                    singleDcl = new SingleDcl();
                }
            }
        }
        return singleDcl;
    }
}
