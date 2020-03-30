package com.slam.dunk.day02.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 10:16
 * @Description:
 */
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer> {

        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        /**
         * source array
         */
        private int[] src;
        /**
         * start index
         */
        private int fromIndex;
        /**
         * end index
         */
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
                    //SleepTools.ms(1);
                    count = count + src[i];
                }
                return count;
            } else {
                //fromIndex....mid....toIndex
                //1...................70....100
                int mid = (fromIndex + toIndex) / 2;
                //divide into two sub-task
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                //invoke all sub tasks
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }


    public static void main(String[] args) {

        //create the fork join pool
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();

        SumTask innerFind = new SumTask(src, 0, src.length - 1);

        long start = System.currentTimeMillis();

        //invoke the task, synchronized
        pool.invoke(innerFind);
        System.out.println("Task is Running.....");

        System.out.println("The count is " + innerFind.join()
                + " spend time:" + (System.currentTimeMillis() - start) + "ms");

    }
}
