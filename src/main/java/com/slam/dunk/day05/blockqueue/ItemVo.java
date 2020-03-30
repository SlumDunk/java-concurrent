package com.slam.dunk.day05.blockqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zerongliu
 * @Date: 3/28/20 21:41
 * @Description:
 */
public class ItemVo<T> implements Delayed {
    /**
     * expire date
     */
    private long activeTime;
    /**
     * data object
     */
    private T data;

    /**
     * @param activeTime the delay time
     * @param data
     */
    public ItemVo(long activeTime, T data) {
        super();
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime,
                TimeUnit.MILLISECONDS) + System.nanoTime();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    /**
     * order by delay time
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return (d == 0) ? 0 : ((d > 0) ? 1 : -1);
    }

    /**
     * return the remain time
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime - System.nanoTime(),
                TimeUnit.NANOSECONDS);
        return d;
    }
}
