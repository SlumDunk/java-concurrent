package com.slam.dunk.day07.transfermoney.service;


import com.slam.dunk.day07.transfermoney.UserAccount;

/**
 *
 */
public interface ITransfer {
    /**
     * @param from   account transfer out
     * @param to     account transfer in
     * @param amount money
     * @throws InterruptedException
     */
    void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException;
}
