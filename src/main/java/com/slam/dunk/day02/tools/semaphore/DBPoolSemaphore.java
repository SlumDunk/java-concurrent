package com.slam.dunk.day02.tools.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 09:32
 * @Description:
 */
public class DBPoolSemaphore {
    private final static int POOL_SIZE = 10;
    /**
     * useful represents the number of db connections that are free
     * useless represents the number of db connections that are occupied
     */
    private final Semaphore useful, useless;

    public DBPoolSemaphore() {
        this.useful = new Semaphore(POOL_SIZE);
        this.useless = new Semaphore(0);
    }

    /**
     * store the db connection
     */
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //initiate the db pool
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    /**
     * return db connection
     *
     * @param connection
     * @throws InterruptedException
     */
    public void returnConnect(Connection connection) throws InterruptedException {
        if (connection != null) {
            System.out.println("the length of waiting queue is " + useful.getQueueLength() + "!"
                    + ", available db connections is :" + useful.availablePermits());
            useless.acquire();
            //synchronous
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    /**
     * get the db connection from pool
     *
     * @return
     * @throws InterruptedException
     */
    public Connection takeConnect() throws InterruptedException {
        useful.acquire();
        Connection conn;
        //synchronous
        synchronized (pool) {
            conn = pool.removeFirst();
        }
        useless.release();
        return conn;
    }
}
