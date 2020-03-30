package com.slam.dunk.day05.blockqueue;

import java.util.concurrent.DelayQueue;

/**
 * @Author: zerongliu
 * @Date: 3/28/20 21:46
 * @Description:
 */
public class PutOrder implements Runnable {
    private DelayQueue<ItemVo<Order>> queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue) {
        super();
        this.queue = queue;
    }

    @Override
    public void run() {

        //expire after 5s
        Order ordeTb = new Order("Tb12345", 366);
        ItemVo<Order> itemTb = new ItemVo<Order>(5000, ordeTb);
        queue.offer(itemTb);
        System.out.println("the order is expired after 5 second:" + ordeTb.getOrderNo());

        //expire after 8s
        Order ordeJd = new Order("Jd54321", 366);
        ItemVo<Order> itemJd = new ItemVo<Order>(8000, ordeJd);
        queue.offer(itemJd);
        System.out.println("the order is expired after 8 seconde:" + ordeJd.getOrderNo());

    }
}
