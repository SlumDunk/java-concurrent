package com.slam.dunk.day08.demo02.vo;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:20
 * @Description:
 */
public class QuestionInDBVo {
    /**
     * id of the question
     */
    private final int id;
    /**
     * encrypted detail of the question
     */
    private final String encryptedDetail;
    /**
     * summary of the question
     */
    private final String sha;

    public QuestionInDBVo(int id, String detail, String sha) {
        this.id = id;
        this.encryptedDetail = detail;
        this.sha = sha;
    }

    public int getId() {
        return id;
    }

    public String getEncryptedDetail() {
        return encryptedDetail;
    }

    public String getSha() {
        return sha;
    }
}
