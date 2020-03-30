package com.slam.dunk.day03.lock.readwrite;

import com.slam.dunk.day03.lock.readwrite.GoodsInfo;
import com.slam.dunk.day03.lock.readwrite.GoodsService;
import com.slam.dunk.utils.SleepTools;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:37
 * @Description:
 */
public class UseSyn implements GoodsService {
    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        SleepTools.ms(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);

    }
}
