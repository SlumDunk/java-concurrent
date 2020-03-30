package com.slam.dunk.day08.demo02.vo;

import java.util.concurrent.Future;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:19
 * @Description:
 */
public class TaskResultVo {
    private final String questionDetail;
    private final Future<QuestionInCacheVo> questionFuture;

    public TaskResultVo(String questionDetail) {
        this.questionDetail = questionDetail;
        this.questionFuture = null;
    }

    public TaskResultVo(Future<QuestionInCacheVo> questionFuture) {
        this.questionDetail = null;
        this.questionFuture = questionFuture;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public Future<QuestionInCacheVo> getQuestionFuture() {
        return questionFuture;
    }

}
