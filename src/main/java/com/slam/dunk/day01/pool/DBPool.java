package com.slam.dunk.day01.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 09:32
 * @Description:
 */
public class DBPool {
    /**
     * DB connection pool
     */
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initalSize) {
        if (initalSize > 0) {
            for (int i = 0; i < initalSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * get connection from pool
     *
     * @param mills timeout
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConn(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long overtime = System.currentTimeMillis() + mills;
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = overtime - System.currentTimeMillis();
                }
                Connection result = null;
                //double check
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

    /**
     * release the connection into pool
     *
     * @param conn
     */
    public void releaseConn(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                pool.notifyAll();
            }
        }
    }
}
