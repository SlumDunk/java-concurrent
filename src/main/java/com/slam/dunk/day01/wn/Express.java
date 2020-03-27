package com.slam.dunk.day01.wn;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 09:19
 * @Description:
 */
public class Express {
    public final static String CITY = "ShangHai";
    /**
     * distance
     */
    private int km;
    /**
     * destination
     */
    private String site;

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * change distance, notify other threads
     */
    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
        //other business code

    }

    /**
     * change destination, notify other threads
     */
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notify();
    }

    public synchronized void waitKm() {
        while (this.km <= 100) {
            try {
                wait();
                System.out.println("check km thread[" + Thread.currentThread().getId()
                        + "] is be notifed.");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("the km is" + this.km + ",I will change db.");

    }

    public synchronized void waitSite() {
        while (CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("check site thread[" + Thread.currentThread().getId()
                        + "] is be notifed.");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("the site is" + this.site + ",I will call user.");
    }
}
