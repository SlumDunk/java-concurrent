package com.slam.dunk.day07.performance;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 19:27
 * @Description:
 */
public class ReduceLock {
    private Map<String, String> matchMap = new HashMap<>();

    /**
     * not a good case
     *
     * @param name
     * @param regexp
     * @return
     */
    public synchronized boolean isMatch(String name, String regexp) {
        String key = "user." + name;
        String job = matchMap.get(key);
        if (job == null) {
            return false;
        } else {
            //cost a lot of time
            return Pattern.matches(regexp, job);
        }
    }

    /**
     * good case
     * <p>
     * reduce the range of the lock
     *
     * @param name
     * @param regexp
     * @return
     */
    public boolean isMatchReduce(String name, String regexp) {
        String key = "user." + name;
        String job;
        synchronized (this) {
            job = matchMap.get(key);
        }

        if (job == null) {
            return false;
        } else {
            return Pattern.matches(regexp, job);
        }
    }
}
