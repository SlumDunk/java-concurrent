package com.slam.dunk.day05.blockqueue;

import java.util.concurrent.DelayQueue;

/**
 * @Author: zerongliu
 * @Date: 3/28/20 21:47
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<ItemVo<Order>> queue = new DelayQueue<>();

        new Thread(new PutOrder(queue)).start();
        new Thread(new FetchOrder(queue)).start();

        //print msg every 0.5s
        for (int i = 1; i < 15; i++) {
            Thread.sleep(500);
            System.out.println(i * 500);
        }
    }
}
