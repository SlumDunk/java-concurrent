package com.slam.dunk.day08.demo02.service.question;

import com.slam.dunk.day08.demo02.dao.MockQuestionDB;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:17
 * @Description:
 */
public class SingleQstService {
    /**
     * parse the question
     *
     * @param questionId
     * @return
     */
    public static String makeQuestion(Integer questionId) {
        return BaseQuestionProcessor.makeQuestion(questionId,
                MockQuestionDB.getQuestion(questionId).getEncryptedDetail());
    }

}
