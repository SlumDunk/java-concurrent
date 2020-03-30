package com.slam.dunk.day08.demo01.pool;

import com.slam.dunk.day08.demo01.service.CheckJobProcessor;
import com.slam.dunk.day08.demo01.service.ITaskProcessor;
import com.slam.dunk.day08.demo01.vo.JobInfo;
import com.slam.dunk.day08.demo01.vo.TaskResult;
import com.slam.dunk.day08.demo01.vo.TaskResultType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:10
 * @Description:
 */
public class PendingJobPool {
    /**
     * number of threads
     */
    private static final int THREAD_COUNTS =
            Runtime.getRuntime().availableProcessors();
    /**
     * task queue
     */
    private static BlockingQueue<Runnable> taskQueue
            = new ArrayBlockingQueue<>(5000);
    /**
     * thread pool
     */
    private static ExecutorService taskExecutor =
            new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS, 60,
                    TimeUnit.SECONDS, taskQueue);
    /**
     * job container
     */
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap
            = new ConcurrentHashMap<>();

    private static CheckJobProcessor checkJob
            = CheckJobProcessor.getInstance();

    public static Map<String, JobInfo<?>> getMap() {
        return jobInfoMap;
    }

    /**
     * single instance
     */
    private PendingJobPool() {
    }

    private static class JobPoolHolder {
        public static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance() {
        return JobPoolHolder.pool;
    }

    /**
     * encapsulate the task as Runnable instance and execute by the thread pool
     *
     * @param <T>
     * @param <R>
     */
    private static class PendingTask<T, R> implements Runnable {

        private JobInfo<R> jobInfo;
        private T processData;

        public PendingTask(JobInfo<R> jobInfo, T processData) {
            super();
            this.jobInfo = jobInfo;
            this.processData = processData;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            R r = null;
            ITaskProcessor<T, R> taskProcessor =
                    (ITaskProcessor<T, R>) jobInfo.getTaskProcessor();
            TaskResult<R> result = null;

            try {
                //call the concrete business method
                result = taskProcessor.taskExecute(processData);
                //check null
                if (result == null) {
                    result = new TaskResult<R>(TaskResultType.Exception, r,
                            "result is null");
                }
                if (result.getResultType() == null) {
                    if (result.getReason() == null) {
                        result = new TaskResult<R>(TaskResultType.Exception, r, "reason is null");
                    } else {
                        result = new TaskResult<R>(TaskResultType.Exception, r,
                                "result is null,but reason:" + result.getReason());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = new TaskResult<R>(TaskResultType.Exception, r,
                        e.getMessage());
            } finally {
                jobInfo.addTaskResult(result, checkJob);
            }
        }
    }

    /**
     * get job by job name
     *
     * @param jobName
     * @param <R>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <R> JobInfo<R> getJob(String jobName) {
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if (null == jobInfo) {
            throw new RuntimeException(jobName + "is an illegal job");
        }
        return jobInfo;
    }

    /**
     * put the task in the specified job
     *
     * @param jobName
     * @param t
     * @param <T>
     * @param <R>
     */
    public <T, R> void putTask(String jobName, T t) {
        JobInfo<R> jobInfo = getJob(jobName);
        PendingTask<T, R> task = new PendingTask<T, R>(jobInfo, t);
        taskExecutor.execute(task);
    }

    /**
     * register job
     *
     * @param jobName
     * @param jobLength
     * @param taskProcessor
     * @param expireTime
     * @param <R>
     */
    public <R> void registerJob(String jobName, int jobLength,
                                ITaskProcessor<?, ?> taskProcessor, long expireTime) {
        JobInfo<R> jobInfo = new JobInfo(jobName, jobLength,
                taskProcessor, expireTime);
        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException(jobName + " is registered successfully");
        }
    }

    /**
     * get the result of the job
     *
     * @param jobName
     * @param <R>
     * @return
     */
    public <R> List<TaskResult<R>> getTaskDetail(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTaskDetail();
    }

    /**
     * get the progress of the job
     *
     * @param jobName
     * @param <R>
     * @return
     */
    public <R> String getTaskProgress(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTotalProcess();
    }
}
