package com.slam.dunk.day08.demo02;

import com.slam.dunk.day08.demo02.constant.Consts;
import com.slam.dunk.day08.demo02.dao.MockQuestionDB;
import com.slam.dunk.day08.demo02.service.ProduceDocService;
import com.slam.dunk.day08.demo02.vo.SrcDocVo;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:14
 * @Description:
 */
public class RpcModeWeb {
    /**
     * thread pool for creating documents
     */
    private static ExecutorService docMakeService
            = Executors.newFixedThreadPool(Consts.CPU_COUNT * 2);

    /**
     * thread pool for uploading documents
     */
    private static ExecutorService docUploadService
            = Executors.newFixedThreadPool(Consts.CPU_COUNT * 2);

    private static CompletionService<String> docCs
            = new ExecutorCompletionService<>(docMakeService);

    private static CompletionService<String> docUploadCs
            = new ExecutorCompletionService<>(docUploadService);

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        System.out.println("init question db begin...........");
        MockQuestionDB.initBank();
        System.out.println("init question db end");

        List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(60);
        long startTotal = System.currentTimeMillis();

        for (SrcDocVo doc : docList) {
            docCs.submit(new MakeDocTask(doc));
        }
        for (SrcDocVo doc : docList) {
            Future<String> future = docCs.take();
            //future.get() is block
            docUploadCs.submit(new UploadDocTask(future.get()));
        }

        //get the upload result
        for (SrcDocVo doc : docList) {
            docUploadCs.take().get();
        }

        System.out.println("------------total cost: "
                + (System.currentTimeMillis() - startTotal) + "ms-------------");
    }

    /**
     * task of creating document
     */
    private static class MakeDocTask implements Callable<String> {

        private SrcDocVo pendingDocVo;

        public MakeDocTask(SrcDocVo pendingDocVo) {
            super();
            this.pendingDocVo = pendingDocVo;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            //String localName = ProduceDocService.makeDoc(pendingDocVo);
            String localName = ProduceDocService.makeDocAsync(pendingDocVo);
            System.out.println("time for creating " + localName + "is: "
                    + (System.currentTimeMillis() - start) + "ms");
            return localName;
        }
    }

    /**
     * upload document
     */
    private static class UploadDocTask implements Callable<String> {

        private String filePath;

        public UploadDocTask(String filePath) {
            super();
            this.filePath = filePath;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            String remoteUrl = ProduceDocService.upLoadDoc(filePath);
            System.out.println("upload to [" + remoteUrl + "] successfully, it costs: "
                    + (System.currentTimeMillis() - start) + "ms");
            return remoteUrl;
        }
    }
}
