package com.slam.dunk.day03.lock.readwrite;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:35
 * @Description:
 */
public class GoodsInfo {
    private final String name;
    /**
     * total sale
     */
    private double totalMoney;
    /**
     * total storage
     */
    private int storeNumber;

    public GoodsInfo(String name, int totalMoney, int storeNumber) {
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNumber = storeNumber;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void changeNumber(int sellNumber) {
        this.totalMoney += sellNumber * 25;
        this.storeNumber -= sellNumber;
    }
}
