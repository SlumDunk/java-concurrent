package com.slam.dunk.day02.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 10:26
 * @Description:
 */
public class FindDirsFiles extends RecursiveAction {
    /**
     * file path
     */
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    public static void main(String[] args) {
        try {
            //create a fork join pool
            ForkJoinPool pool = new ForkJoinPool();
            FindDirsFiles task = new FindDirsFiles(new File("/"));

            //execute the task asynchronous
            pool.execute(task);

            //do other work
            System.out.println("Task is Running......");
            Thread.sleep(1);
            int otherWork = 0;
            for (int i = 0; i < 100; i++) {
                otherWork = otherWork + i;
            }
            System.out.println("Main Thread done sth......,otherWork=" + otherWork);
            //wait for the completion of the task and return the result
            //block
            task.join();
            System.out.println("Task end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void compute() {

        List<FindDirsFiles> subTasks = new ArrayList<>();

        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //if it is directory, create the sub task
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    //check the file
                    if (file.getAbsolutePath().endsWith("txt")) {
                        System.out.println("file:" + file.getAbsolutePath());
                    }
                }
            }
            if (!subTasks.isEmpty()) {
                for (FindDirsFiles subTask : invokeAll(subTasks)) {
                    //wait for subtask, block
                    subTask.join();
                }
            }
        }


    }
}
