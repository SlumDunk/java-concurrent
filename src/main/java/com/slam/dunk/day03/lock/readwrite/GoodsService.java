package com.slam.dunk.day03.lock.readwrite;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:36
 * @Description:
 */
public interface GoodsService {
    /**
     * get goods number
     *
     * @return
     */
    public GoodsInfo getNum();

    /**
     * set goods number
     *
     * @param number
     */
    public void setNum(int number);
}
