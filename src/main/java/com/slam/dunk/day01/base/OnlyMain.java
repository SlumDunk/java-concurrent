package com.slam.dunk.day01.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 21:55
 * @Description:
 */
public class OnlyMain {
    public static void main(String[] args) {
        //thread management interface of the JVM
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos
        ) {
            System.out.println("[" + threadInfo.getThreadName() + "]" + threadInfo.getThreadId());
        }
    }
}
