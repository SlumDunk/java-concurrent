package com.slam.dunk.day08.demo02.service.question;

import com.slam.dunk.day08.demo02.vo.MockBusiness;

import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:16
 * @Description:
 */
public class BaseQuestionProcessor {
    /**
     * mock the business of creating question, parse text, download images and so on
     *
     * @param questionId
     * @param questionSrc
     * @return
     */
    public static String makeQuestion(Integer questionId, String questionSrc) {
        Random r = new Random();
        MockBusiness.business(450 + r.nextInt(100));
        return "CompleteQuestion[id=" + questionId
                + " content=:" + questionSrc + "]";
    }
}
