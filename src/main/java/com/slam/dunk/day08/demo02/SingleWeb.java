package com.slam.dunk.day08.demo02;

import com.slam.dunk.day08.demo02.dao.MockQuestionDB;
import com.slam.dunk.day08.demo02.service.ProduceDocService;
import com.slam.dunk.day08.demo02.vo.SrcDocVo;

import java.util.List;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:18
 * @Description:
 */
public class SingleWeb {
    public static void main(String[] args) {
        System.out.println("init question db begin...........");
        MockQuestionDB.initBank();
        System.out.println("init question db end");


        List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(2);
        long startTotal = System.currentTimeMillis();
        for (SrcDocVo doc : docList) {
            System.out.println("start to handle the document: " + doc.getDocName() + ".......");
            long start = System.currentTimeMillis();
            String localName = ProduceDocService.makeDoc(doc);
            System.out.println("time to create " + localName + "："
                    + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            String remoteUrl = ProduceDocService.upLoadDoc(localName);
            System.out.println("upload to [" + remoteUrl + "] sucessfully, it costs："
                    + (System.currentTimeMillis() - start) + "ms");
        }
        System.out.println("------------total time cost is："
                + (System.currentTimeMillis() - startTotal) + "ms-------------");
    }
}
