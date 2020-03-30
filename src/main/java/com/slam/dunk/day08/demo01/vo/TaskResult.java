package com.slam.dunk.day08.demo01.vo;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 09:05
 * @Description:
 */
public class TaskResult<R> {
    /**
     * result type
     */
    private final TaskResultType resultType;
    /**
     * result data
     */
    private final R returnValue;
    /**
     * the reason of failure
     */
    private final String reason;

    public TaskResult(TaskResultType resultType, R returnValue, String reason) {
        super();
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = reason;
    }

    /**
     * call by sucess
     *
     * @param resultType
     * @param returnValue
     */
    public TaskResult(TaskResultType resultType, R returnValue) {
        super();
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = "Success";
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public R getReturnValue() {
        return returnValue;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "TaskResult [resultType=" + resultType
                + ", returnValue=" + returnValue
                + ", reason=" + reason + "]";
    }

}
