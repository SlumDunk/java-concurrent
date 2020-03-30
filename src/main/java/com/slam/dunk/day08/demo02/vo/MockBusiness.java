package com.slam.dunk.day08.demo02.vo;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:13
 * @Description:
 */
public class MockBusiness {
    /**
     * mock the business
     *
     * @param sleepTime
     */
    public static void business(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
