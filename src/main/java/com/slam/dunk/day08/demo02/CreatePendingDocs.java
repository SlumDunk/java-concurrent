package com.slam.dunk.day08.demo02;

import com.slam.dunk.day08.demo02.constant.Consts;
import com.slam.dunk.day08.demo02.vo.SrcDocVo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:11
 * @Description: generate the question list of documents
 */
public class CreatePendingDocs {

    /**
     * create a list of documents
     *
     * @param count
     * @return
     */
    public static List<SrcDocVo> makePendingDoc(int count) {
        Random r = new Random();
        //list of documents
        List<SrcDocVo> docList = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> questionList = new LinkedList<Integer>();
            //questions in each doc
            for (int j = 0; j < Consts.QUESTION_COUNT_IN_DOC; j++) {
                int questionId = r.nextInt(Consts.SIZE_OF_QUESTION_DB);
                questionList.add(questionId);
            }
            SrcDocVo pendingDocVo = new SrcDocVo("pending_" + i,
                    questionList);
            docList.add(pendingDocVo);
        }
        return docList;
    }
}
