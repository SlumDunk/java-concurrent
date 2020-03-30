package com.slam.dunk.day07.safeclass;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:23
 * @Description: unchanged class, it is safe
 */
public class ImmutableToo {
    /**
     * add final as possible
     */
    private final List<Integer> list = new ArrayList<>(3);

    public ImmutableToo() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public boolean isContains(int i) {
        return list.contains(i);
    }
}
