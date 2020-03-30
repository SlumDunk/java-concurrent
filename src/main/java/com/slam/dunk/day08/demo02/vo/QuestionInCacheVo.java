package com.slam.dunk.day08.demo02.vo;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:22
 * @Description:
 */
public class QuestionInCacheVo {
    private final String questionDetail;
    private final String questionSha;

    public QuestionInCacheVo(String questionDetail, String questionSha) {
        super();
        this.questionDetail = questionDetail;
        this.questionSha = questionSha;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public String getQuestionSha() {
        return questionSha;
    }
}
