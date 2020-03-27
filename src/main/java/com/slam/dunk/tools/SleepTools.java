package com.slam.dunk.tools;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 22:56
 * @Description:
 */
public class SleepTools {
    /**
     * sleep for seconds
     *
     * @param seconds
     */
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * sleep for mill seconds
     *
     * @param millSeconds
     */
    public static final void ms(int millSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(millSeconds);
        } catch (InterruptedException e) {
        }
    }
}
