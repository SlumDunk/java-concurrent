package com.slam.dunk.day07.safeclass;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:18
 * @Description:
 */
public class UnsafePublish {

    /**
     * replace by concurrent container or return the copy of the list
     */
    private List<Integer> list = new ArrayList<>(3);

    public UnsafePublish() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * it is unsafe in multi-thread
     *
     * @return
     */
    public List<Integer> getList() {
        return list;
    }

    /**
     * it is safe
     *
     * @param index
     * @return
     */
    public synchronized int getList(int index) {
        return list.get(index);
    }

    public synchronized void set(int index, int val) {
        list.set(index, val);
    }
}
