package com.slam.dunk.day03.lock.readwrite;

import com.slam.dunk.utils.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:38
 * @Description:
 */
public class UseRwLock implements GoodsService {
    private GoodsInfo goodsInfo;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * read lock
     */
    private final Lock getLock = lock.readLock();
    /**
     * write lock
     */
    private final Lock setLock = lock.writeLock();

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            getLock.unlock();
        }

    }

    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        } finally {
            setLock.unlock();
        }
    }
}
