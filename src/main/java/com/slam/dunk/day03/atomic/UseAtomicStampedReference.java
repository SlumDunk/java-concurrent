package com.slam.dunk.day03.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 15:17
 * @Description:
 */
public class UseAtomicStampedReference {

    static AtomicStampedReference<String> asr =
            new AtomicStampedReference<>("Mark", 0);


    public static void main(String[] args) throws InterruptedException {
        //initial version number
        final int oldStamp = asr.getStamp();
        final String oldReference = asr.getReference();

        System.out.println(oldReference + "===========" + oldStamp);

        Thread rightStampThread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()
                        + "current value is：" + oldReference + ", current version is：" + oldStamp + "-"
                        + asr.compareAndSet(oldReference, oldReference + "Java",
                        oldStamp, oldStamp + 1));

            }

        });

        Thread errorStampThread = new Thread(new Runnable() {

            @Override
            public void run() {
                String reference = asr.getReference();
                System.out.println(Thread.currentThread().getName()
                        + "current variable is ：" + reference + ", current version number is：" + asr.getStamp() + "-"
                        + asr.compareAndSet(reference, reference + "C",
                        oldStamp, oldStamp + 1));

            }

        });

        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();
        System.out.println(asr.getReference() + "===========" + asr.getStamp());

    }
}
