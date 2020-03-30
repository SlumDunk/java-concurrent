package com.slam.dunk.day03.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 16:48
 * @Description:
 */
public class ExpressCond {
    public final static String CITY = "ShangHai";
    /**
     * distance
     */
    private int km;
    /**
     * destination
     */
    private String site;
    private Lock lock = new ReentrantLock();
    private Condition keCond = lock.newCondition();
    private Condition siteCond = lock.newCondition();

    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * change km
     */
    public void changeKm() {
        lock.lock();
        try {
            this.km = 101;
            keCond.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * change site
     */
    public void changeSite() {
        lock.lock();
        try {
            this.site = "BeiJing";
            siteCond.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * wait for the km be satisfied
     */
    public void waitKm() {
        lock.lock();
        try {
            while (this.km <= 100) {
                try {
                    keCond.await();
                    System.out.println("check km thread[" + Thread.currentThread().getId()
                            + "] is be notifed.");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }

        System.out.println("the Km is " + this.km + ",I will change db");
    }

    /**
     * wait for the destination be satisfied
     */
    public void waitSite() {
        lock.lock();
        try {
            while (CITY.equals(this.site)) {
                try {
                    siteCond.await();
                    System.out.println("check site thread[" + Thread.currentThread().getId()
                            + "] is be notifed.");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println("the site is " + this.site + ",I will call user");
    }
}
