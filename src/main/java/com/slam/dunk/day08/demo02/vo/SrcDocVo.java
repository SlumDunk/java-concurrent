package com.slam.dunk.day08.demo02.vo;

import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:19
 * @Description:
 */
public class SrcDocVo {
    /**
     * name of the doc
     */
    private final String docName;
    /**
     * id list of the questions
     */
    private final List<Integer> questionList;

    public SrcDocVo(String docName, List<Integer> questionList) {
        this.docName = docName;
        this.questionList = questionList;
    }

    public String getDocName() {
        return docName;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }
}
