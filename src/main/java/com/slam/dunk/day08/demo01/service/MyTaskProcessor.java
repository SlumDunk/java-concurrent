package com.slam.dunk.day08.demo01.service;

import com.slam.dunk.day08.demo01.vo.TaskResult;
import com.slam.dunk.day08.demo01.vo.TaskResultType;
import com.slam.dunk.utils.SleepTools;

import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:12
 * @Description:
 */
public class MyTaskProcessor implements ITaskProcessor<Integer, Integer> {
    @Override
    public TaskResult<Integer> taskExecute(Integer data) {
        Random r = new Random();
        int flag = r.nextInt(500);
        SleepTools.ms(flag);
        //normal situation
        if (flag <= 300) {
            Integer returnValue = data.intValue() + flag;
            return new TaskResult<Integer>(TaskResultType.Success, returnValue);
        } else if (flag > 301 && flag <= 400) {
            //simulate the failure situation
            return new TaskResult<Integer>(TaskResultType.Failure, -1, "Failure");
        } else {
            //simulate the exception situation
            try {
                throw new RuntimeException("exception happen!!");
            } catch (Exception e) {
                return new TaskResult<Integer>(TaskResultType.Exception,
                        -1, e.getMessage());
            }
        }
    }
}
