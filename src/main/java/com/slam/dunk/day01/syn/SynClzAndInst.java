package com.slam.dunk.day01.syn;

import com.slam.dunk.tools.SleepTools;

/**
 * @Author: zerongliu
 * @Date: 3/26/20 23:11
 * @Description:
 */
public class SynClzAndInst {
    /**
     * the thread that uses class lock
     */
    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    /**
     * the thread that uses the instance lock
     */
    private static class InstanceSyn implements Runnable {
        private SynClzAndInst synClzAndInst;

        public InstanceSyn(SynClzAndInst synClzAndInst) {
            this.synClzAndInst = synClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..." + synClzAndInst);
            synClzAndInst.instance();
        }
    }

    /**
     * the thread that uses the instance lock
     */
    private static class Instance2Syn implements Runnable {
        private SynClzAndInst synClzAndInst;

        public Instance2Syn(SynClzAndInst synClzAndInst) {
            this.synClzAndInst = synClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running..." + synClzAndInst);
            synClzAndInst.instance2();
        }
    }

    /**
     * instance lock
     */
    private synchronized void instance() {
        SleepTools.second(3);
        System.out.println("synInstance is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance ended " + this.toString());
    }

    /**
     * instance lock
     */
    private synchronized void instance2() {
        SleepTools.second(3);
        System.out.println("synInstance2 is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 ended " + this.toString());
    }

    /**
     * class lock
     */
    private static synchronized void synClass() {
        SleepTools.second(1);
        System.out.println("synClass going...");
        SleepTools.second(1);
        System.out.println("synClass end");
    }

    public static void main(String[] args) {
        SynClzAndInst synClzAndInst = new SynClzAndInst();
        Thread t1 = new Thread(new InstanceSyn(synClzAndInst));

        //pay attention to the difference
        SynClzAndInst synClzAndInst2 = new SynClzAndInst();
        Thread t2 = new Thread(new Instance2Syn(synClzAndInst));

        t1.start();
        t2.start();

        //lock the class
        SynClass synClass = new SynClass();
        synClass.start();
        SleepTools.second(1);
    }
}
