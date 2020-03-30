package com.slam.dunk.day07.transfermoney.service;


import com.slam.dunk.day07.transfermoney.UserAccount;

/**
 *
 */
public class SafeOperate implements ITransfer {

    /**
     * inner lock
     */
    private static Object tieLock = new Object();

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException {

        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        //lock the account with small hash first
        if (fromHash < toHash) {
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
        } else if (toHash < fromHash) {
            synchronized (to) {
                System.out.println(Thread.currentThread().getName()
                        + " get" + to.getName());
                Thread.sleep(100);
                synchronized (from) {
                    System.out.println(Thread.currentThread().getName()
                            + " get" + from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                }
            }
        } else {
            //deal with the situation that two accounts have the same hash
            synchronized (tieLock) {
                synchronized (from) {
                    synchronized (to) {
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }

    }
}
