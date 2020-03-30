package com.slam.dunk.day08.demo01.vo;

import com.slam.dunk.day08.demo01.service.CheckJobProcessor;
import com.slam.dunk.day08.demo01.service.ITaskProcessor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:07
 * @Description:
 */
public class JobInfo<R> {
    /**
     * job name, unique
     */
    private final String jobName;
    /**
     * length of this tasks
     */
    private final int taskLength;
    /**
     * task processor
     */
    private final ITaskProcessor<?, ?> taskProcessor;
    /**
     * success tasks
     */
    private final AtomicInteger successCount;
    /**
     * tasks have been done
     */
    private final AtomicInteger taskProcessedCount;
    /**
     * result queue
     */
    private final LinkedBlockingDeque<TaskResult<R>> taskDetailQueue;
    /**
     * expire time
     */
    private final long expireTime;


    public JobInfo(String jobName, int taskLength,
                   ITaskProcessor<?, ?> taskProcessor,
                   long expireTime) {
        super();
        this.jobName = jobName;
        this.taskLength = taskLength;
        this.taskProcessor = taskProcessor;
        this.successCount = new AtomicInteger(0);
        this.taskProcessedCount = new AtomicInteger(0);
        this.taskDetailQueue = new LinkedBlockingDeque<TaskResult<R>>(taskLength);
        ;
        this.expireTime = expireTime;
    }

    public ITaskProcessor<?, ?> getTaskProcessor() {
        return taskProcessor;
    }

    /**
     * return success count
     *
     * @return
     */
    public int getSuccessCount() {
        return successCount.get();
    }

    /**
     * return number of the tasks that have been done
     *
     * @return
     */
    public int getTaskProcessedCount() {
        return taskProcessedCount.get();
    }

    /**
     * return fail count
     *
     * @return
     */
    public int getFailCount() {
        return taskProcessedCount.get() - successCount.get();
    }

    public String getTotalProcess() {
        return "Success[" + successCount.get() + "]/Current["
                + taskProcessedCount.get() + "] Total[" + taskLength + "]";
    }

    /**
     * return the result detail of each task
     *
     * @return
     */
    public List<TaskResult<R>> getTaskDetail() {
        List<TaskResult<R>> taskList = new LinkedList<>();
        TaskResult<R> taskResult;
        while ((taskResult = taskDetailQueue.pollFirst()) != null) {
            taskList.add(taskResult);
        }
        return taskList;
    }

    /**
     * set result of tasks, just guarantee eventually consistency
     *
     * @param result
     * @param checkJob
     */
    public void addTaskResult(TaskResult<R> result, CheckJobProcessor checkJob) {
        if (TaskResultType.Success.equals(result.getResultType())) {
            successCount.incrementAndGet();
        }
        taskDetailQueue.addLast(result);
        taskProcessedCount.incrementAndGet();

        //if all the tasks have been done, check the expired job by the daemon thread
        if (taskProcessedCount.get() == taskLength) {
            checkJob.putJob(jobName, expireTime);
        }

    }
}
