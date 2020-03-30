package com.slam.dunk.day08.demo01.service;

import com.slam.dunk.day08.demo01.vo.TaskResult;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:04
 * @Description:
 */
public interface ITaskProcessor<T, R> {
    /**
     * define how to deal with the business, implements by instance
     * @param data
     * @return
     */
    TaskResult<R> taskExecute(T data);
}
