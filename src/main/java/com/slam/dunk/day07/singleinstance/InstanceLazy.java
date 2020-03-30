package com.slam.dunk.day07.singleinstance;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 19:49
 * @Description:
 */
public class InstanceLazy {
    private Integer value;
    /**
     * may be a huge array that costs a lot of time to init, but haven't been used frequently
     */
    private Integer val;

    public InstanceLazy(Integer value) {
        super();
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    /**
     * use static inner class to lazy initiation
     */
    private static class ValHolder {
        public static Integer vHolder = new Integer(1000000);
    }

    public Integer getVal() {
        return ValHolder.vHolder;
    }

}
