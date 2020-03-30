package com.slam.dunk.day08.demo02.constant;

/**
 * @Author: zerongliu
 * @Date: 3/30/20 11:10
 * @Description:
 */
public class Consts {
    /**
     * number of cpu cores
     */
    public final static int CPU_COUNT
            = Runtime.getRuntime().availableProcessors();

    /**
     * number of question in each doc
     */
    public static final int QUESTION_COUNT_IN_DOC = 80;
    /**
     * total questions
     */
    public static final int SIZE_OF_QUESTION_DB = 2000;
    /**
     * length of each question
     */
    public static final int QUESTION_LENGTH = 800;
}
