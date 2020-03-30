package com.slam.dunk.day08.demo02.service;

import com.slam.dunk.day08.demo02.vo.MockBusiness;
import com.slam.dunk.day08.demo02.service.question.ParallaxQstService;
import com.slam.dunk.day08.demo02.service.question.SingleQstService;
import com.slam.dunk.day08.demo02.vo.SrcDocVo;
import com.slam.dunk.day08.demo02.vo.TaskResultVo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:15
 * @Description:
 */
public class ProduceDocService {
    /**
     * upload file to cloud
     *
     * @param docFileName
     * @return
     */
    public static String upLoadDoc(String docFileName) {
        Random r = new Random();
        MockBusiness.business(9000 + r.nextInt(400));
        return "http://www.xxxx.com/file/upload/" + docFileName;
    }

    /**
     * create pdf synchronously
     *
     * @param pendingDocVo
     * @return
     */
    public static String makeDoc(SrcDocVo pendingDocVo) {
        System.out.println("start to deal with the doc " + pendingDocVo.getDocName());
        StringBuffer sb = new StringBuffer();
        //handle each question
        for (Integer questionId : pendingDocVo.getQuestionList()) {
            sb.append(SingleQstService.makeQuestion(questionId));
        }
        return "complete_" + System.currentTimeMillis() + "_"
                + pendingDocVo.getDocName() + ".pdf";
    }

    /**
     * create the doc asynchronously
     *
     * @param pendingDocVo
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static String makeDocAsync(SrcDocVo pendingDocVo) throws
            InterruptedException, ExecutionException {
        System.out.println("start to handle the doc ：" + pendingDocVo.getDocName());

        Map<Integer, TaskResultVo> qstResultMap = new HashMap<>();
        //handle the questions asynchronously
        for (Integer questionId : pendingDocVo.getQuestionList()) {
            qstResultMap.put(questionId, ParallaxQstService.makeQuestion(questionId));
        }
        StringBuffer sb = new StringBuffer();
        for (Integer questionId : pendingDocVo.getQuestionList()) {
            TaskResultVo result = qstResultMap.get(questionId);
            //future.get() is block
            sb.append(result.getQuestionDetail() == null ?
                    result.getQuestionFuture().get().getQuestionDetail()
                    : result.getQuestionDetail());
        }
        return "complete_" + System.currentTimeMillis() + "_"
                + pendingDocVo.getDocName() + ".pdf";

    }

}
