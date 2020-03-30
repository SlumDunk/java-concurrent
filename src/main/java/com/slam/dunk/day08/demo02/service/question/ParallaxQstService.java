package com.slam.dunk.day08.demo02.service.question;

import com.slam.dunk.day08.demo02.constant.Consts;
import com.slam.dunk.day08.demo02.dao.MockQuestionDB;
import com.slam.dunk.day08.demo02.vo.QuestionInCacheVo;
import com.slam.dunk.day08.demo02.vo.QuestionInDBVo;
import com.slam.dunk.day08.demo02.vo.TaskResultVo;

import java.util.concurrent.*;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:17
 * @Description: use cache to improve the performance of creating documents
 */
public class ParallaxQstService {
    /**
     * cache of the questions
     */
    private static ConcurrentHashMap<Integer, QuestionInCacheVo> questionCache
            = new ConcurrentHashMap<>();

    /**
     * cache of questions are processing
     */
    private static ConcurrentHashMap<Integer, Future<QuestionInCacheVo>>
            processingQuestionCache = new ConcurrentHashMap<>();

    private static ExecutorService makeQuestionService
            = Executors.newFixedThreadPool(Consts.CPU_COUNT * 2);

    /**
     * create question
     *
     * @param questionId
     * @return
     */
    public static TaskResultVo makeQuestion(Integer questionId) {
        QuestionInCacheVo qstCacheVo = questionCache.get(questionId);
        if (null == qstCacheVo) {
            System.out.println("......question [" + questionId + "] isn't in the cache，"
                    + " prepare to start the job.");
            return new TaskResultVo(getQstFuture(questionId));
        } else {
            //get the summary of the question
            String questionSha = MockQuestionDB.getSha(questionId);
            if (questionSha.equals(qstCacheVo.getQuestionSha())) {
                System.out.println("......question [" + questionId + "] is in the cache without change.");
                return new TaskResultVo(qstCacheVo.getQuestionDetail());
            } else {
                System.out.println("......question [" + questionId + "] is in the cache, "
                        + " but it is changed, need to read from db again");
                return new TaskResultVo(getQstFuture(questionId));
            }
        }
    }

    /**
     * get the questions asynchronously
     *
     * @param questionId
     * @return
     */
    private static Future<QuestionInCacheVo> getQstFuture(Integer questionId) {
        Future<QuestionInCacheVo> questionFuture
                = processingQuestionCache.get(questionId);
        try {
            if (questionFuture == null) {
                //get the encrypted question
                QuestionInDBVo qstDbVo = MockQuestionDB.getQuestion(questionId);
                QuestionTask questionTask = new QuestionTask(qstDbVo, questionId);
                FutureTask<QuestionInCacheVo> ft
                        = new FutureTask<>(questionTask);
                //guarantee thread safety
                //return the previous value related to this key
                questionFuture = processingQuestionCache.putIfAbsent(questionId,
                        ft);
                if (questionFuture == null) {
                    //occupy in the map first
                    questionFuture = ft;
                    makeQuestionService.execute(ft);
                    System.out.println("start the job to generate the [" + questionId + "] successfully, please wait for completion>>>>>>>>");
                } else {
                    System.out.println("<<<<<<<<<<< other thread has begun the task of creating the question [" + questionId
                            + "], this thread doesn't need to do it again");
                }
            } else {
                System.out.println("there is a task doing this job of creating question [" + questionId + "]");
            }
        } catch (Exception e) {
            processingQuestionCache.remove(questionId);
            e.printStackTrace();
            throw e;

        }
        return questionFuture;
    }


    /**
     * task of parsing the question
     */
    private static class QuestionTask implements Callable<QuestionInCacheVo> {

        private QuestionInDBVo qstDbVo;
        private Integer questionId;

        public QuestionTask(QuestionInDBVo qstDbVo, Integer questionId) {
            super();
            this.qstDbVo = qstDbVo;
            this.questionId = questionId;
        }

        @Override
        public QuestionInCacheVo call() throws Exception {
            try {
                //parse the detail of the question
                String qstDetail = BaseQuestionProcessor.makeQuestion(questionId,
                        qstDbVo.getEncryptedDetail());
                String questionSha = qstDbVo.getSha();
                QuestionInCacheVo qstCache = new QuestionInCacheVo(qstDetail, questionSha);
                questionCache.put(questionId, qstCache);
                return qstCache;
            } finally {
                //remove the job
                processingQuestionCache.remove(questionId);
            }
        }

    }

}
