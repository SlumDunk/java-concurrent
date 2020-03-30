package com.slam.dunk.day07.transfermoney.service;

import com.slam.dunk.day07.transfermoney.UserAccount;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:50
 * @Description: unsafe transfer action deadlock
 */
public class TransferAccount implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName()
                    + " get" + from.getName());
            Thread.sleep(100);
            synchronized (to) {
                System.out.println(Thread.currentThread().getName()
                        + " get" + to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }
}
