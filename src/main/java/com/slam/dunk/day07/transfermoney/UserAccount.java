package com.slam.dunk.day07.transfermoney;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:49
 * @Description:
 */
public class UserAccount {
    /**
     * account name
     */
    private final String name;
    /**
     * account amount
     */
    private int money;

    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public UserAccount(String name, int amount) {
        this.name = name;
        this.money = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    /**
     * import money
     *
     * @param amount
     */
    public void addMoney(int amount) {
        money = money + amount;
    }

    /**
     * export money
     *
     * @param amount
     */
    public void flyMoney(int amount) {
        money = money - amount;
    }
}
