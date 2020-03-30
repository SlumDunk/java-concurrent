package com.slam.dunk.day07.singleinstance;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 19:44
 * @Description:
 */
public class SingleInit {
    private SingleInit() {
    }

    static {
        System.out.println("hhhh");
    }

    /**
     * a private class that holds the instance
     * <p>
     * if we don't call this class, it is just a fragment of code
     * only when we getInstance(), this class will be loaded and initialized
     */
    private static class InstanceHolder {
        static {
            System.out.println("hello world");
        }

        public static SingleInit instance = new SingleInit();
    }

    public static SingleInit getInstance() {
        return InstanceHolder.instance;
    }

    public static void main(String[] args) {
        SingleInit.getInstance();
    }

}
