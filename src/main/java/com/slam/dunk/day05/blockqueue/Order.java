package com.slam.dunk.day05.blockqueue;

/**
 * @Author: zerongliu
 * @Date: 3/28/20 21:45
 * @Description:
 */
public class Order {
    private final String orderNo;
    private final double orderMoney;

    public Order(String orderNo, double orderMoney) {
        super();
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }
}
