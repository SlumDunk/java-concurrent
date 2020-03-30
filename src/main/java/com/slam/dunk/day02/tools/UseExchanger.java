package com.slam.dunk.day02.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 14:50
 * @Description:
 */
public class UseExchanger {
    private static final Exchanger<Set<String>> exchange
            = new Exchanger<Set<String>>();

    public static void main(String[] args) {

        //the first thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                //store data
                Set<String> setA = new HashSet<String>();
                try {
                    //add data
                    setA.add("Trump");
                    setA.add("stupid");
                    //exchange data
                    setA = exchange.exchange(setA);
                    //deal with data

                    System.out.println(Thread.currentThread().getId() + ", first: " + setA.toString());
                } catch (InterruptedException e) {
                }
            }
        }).start();

        //the second thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                //store data
                Set<String> setB = new HashSet<String>();
                try {
                    //add data
                    setB.add("hello");
                    setB.add("world");
                    //exchange data
                    setB = exchange.exchange(setB);
                    //deal with data
                    System.out.println(Thread.currentThread().getId() + ", second: " + setB.toString());
                } catch (InterruptedException e) {
                }
            }
        }).start();

    }
}
