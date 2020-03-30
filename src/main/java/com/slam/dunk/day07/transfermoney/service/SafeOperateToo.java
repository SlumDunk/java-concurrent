package com.slam.dunk.day07.transfermoney.service;

import com.slam.dunk.day07.transfermoney.UserAccount;
import com.slam.dunk.utils.SleepTools;

import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:50
 * @Description: use tryLock to solve the deadlock
 */
public class SafeOperateToo implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {
        Random r = new Random();
        while (true) {
            if (from.getLock().tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName()
                            + " get " + from.getName());
                    if (to.getLock().tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName()
                                    + " get " + to.getName());
                            //acquire two locks
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            break;
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }
            //live lock, we need to add this sleep time to avoid this
            SleepTools.ms(r.nextInt(10));
        }
    }
}
