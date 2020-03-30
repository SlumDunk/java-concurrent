package com.slam.dunk.day08.demo01.vo;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:05
 * @Description:
 */
public enum TaskResultType {
    /**
     * success
     */
    Success,
    /**
     * failure, run normal, but the result is wrong
     */
    Failure,
    /**
     * execute exception
     */
    Exception;
}
