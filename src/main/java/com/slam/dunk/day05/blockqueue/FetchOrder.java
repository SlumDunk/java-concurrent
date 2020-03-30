package com.slam.dunk.day05.blockqueue;

import java.util.concurrent.DelayQueue;

/**
 * @Author: zerongliu
 * @Date: 3/28/20 21:47
 * @Description:
 */
public class FetchOrder implements Runnable {
    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue) {
        super();
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ItemVo<Order> item = queue.take();
                Order order = (Order) item.getData();
                System.out.println("get from queue:" + order.getOrderNo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
