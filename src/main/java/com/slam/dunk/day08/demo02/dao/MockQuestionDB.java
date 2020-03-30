package com.slam.dunk.day08.demo02.dao;

import com.slam.dunk.day08.demo02.vo.MockBusiness;
import com.slam.dunk.day08.demo02.constant.Consts;
import com.slam.dunk.day08.demo02.util.EncryptUtils;
import com.slam.dunk.day08.demo02.vo.QuestionInDBVo;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:14
 * @Description:
 */
public class MockQuestionDB {
    /**
     * store the question
     */
    private static ConcurrentHashMap<Integer, QuestionInDBVo> questionBankMap
            = new ConcurrentHashMap<>();
    /**
     * update the question DB at fix rate
     */
    private static ScheduledExecutorService updateQuestionBank
            = new ScheduledThreadPoolExecutor(1);

    /**
     * init the DB
     */
    public static void initBank() {
        for (int i = 0; i < Consts.SIZE_OF_QUESTION_DB; i++) {
            String questionContent = makeQuestionDetail(Consts.QUESTION_LENGTH);
            questionBankMap.put(i, new QuestionInDBVo(i,
                    questionContent, EncryptUtils.EncryptBySHA1(questionContent)));
        }
        //start the job to update the question DB at fix rate
        updateQuestionTimer();
    }

    /**
     * create question
     *
     * @param length length of the question
     * @return
     */
    private static String makeQuestionDetail(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * get the question
     *
     * @param id
     * @return
     */
    public static QuestionInDBVo getQuestion(int id) {
        //mock the progress of reading DB
        MockBusiness.business(20);
        return questionBankMap.get(id);
    }

    /**
     * get the summary of the question
     *
     * @param id
     * @return
     */
    public static String getSha(int id) {
        //mock the progress of reading DB
        MockBusiness.business(10);
        return questionBankMap.get(id).getSha();
    }

    /**
     * job of update question db
     */
    private static class UpdateBank implements Runnable {

        @Override
        public void run() {
            Random random = new Random();
            int questionId = random.nextInt(Consts.SIZE_OF_QUESTION_DB);
            String questionContent = makeQuestionDetail(Consts.QUESTION_LENGTH);
            questionBankMap.put(questionId, new QuestionInDBVo(questionId,
                    questionContent, EncryptUtils.EncryptBySHA1(questionContent)));
            System.out.println("question【" + questionId + "】has been updated!!");
        }
    }

    /**
     * update question db
     */
    private static void updateQuestionTimer() {
        System.out.println("start to update the question db at fixed rate..........................");
        updateQuestionBank.scheduleAtFixedRate(new UpdateBank(),
                15, 5, TimeUnit.SECONDS);
    }
}
