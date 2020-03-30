package com.slam.dunk.day03.lock.readwrite;

import com.slam.dunk.utils.SleepTools;

import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:39
 * @Description:
 */
public class TestApp {
    /**
     *
     */
    static final int readWriteRatio = 10;
    /**
     * min thread count
     */
    static final int minThreadCount = 3;
    //static CountDownLatch latch= new CountDownLatch(1);

    /**
     * read thread
     */
    private static class GetThread implements Runnable {

        private GoodsService goodsService;

        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                goodsService.getNum();
            }
            System.out.println(Thread.currentThread().getName() + "time for reading the goods information："
                    + (System.currentTimeMillis() - start) + "ms");

        }
    }

    /**
     * write thread
     */
    private static class SetThread implements Runnable {

        private GoodsService goodsService;

        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                SleepTools.ms(50);
                goodsService.setNum(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName()
                    + "time for writing the goods：" + (System.currentTimeMillis() - start) + "ms---------");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        GoodsInfo goodsInfo = new GoodsInfo("Cup", 100000, 10000);
        GoodsService goodsService = new UseSyn(goodsInfo);
//        GoodsService goodsService = new UseRwLock(goodsInfo);
        for (int i = 0; i < minThreadCount; i++) {
            Thread setT = new Thread(new SetThread(goodsService));
            for (int j = 0; j < readWriteRatio; j++) {
                Thread getT = new Thread(new GetThread(goodsService));
                getT.start();
            }
            SleepTools.ms(100);
            setT.start();
        }

    }
}
