package com.slam.dunk.day07.transfermoney;

import com.slam.dunk.day07.transfermoney.service.ITransfer;
import com.slam.dunk.day07.transfermoney.service.SafeOperateToo;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:50
 * @Description:
 */
public class PayCompany {
    /**
     * execute transfer business
     */
    private static class TransferThread extends Thread {

        /**
         * name of thread
         */
        private String name;
        private UserAccount from;
        private UserAccount to;
        private int amount;
        /**
         * transfer service
         */
        private ITransfer transfer;

        public TransferThread(String name, UserAccount from, UserAccount to,
                              int amount, ITransfer transfer) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.transfer = transfer;
        }


        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                transfer.transfer(from, to, amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PayCompany payCompany = new PayCompany();
        UserAccount zhangsan = new UserAccount("zhangsan", 20000);
        UserAccount lisi = new UserAccount("lisi", 20000);
        ITransfer transfer = new SafeOperateToo();
        TransferThread zhangsanToLisi = new TransferThread("zhangsanToLisi"
                , zhangsan, lisi, 2000, transfer);
        TransferThread lisiToZhangsan = new TransferThread("lisiToZhangsan"
                , lisi, zhangsan, 4000, transfer);
        zhangsanToLisi.start();
        lisiToZhangsan.start();

    }

}
