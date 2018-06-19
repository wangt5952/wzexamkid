package cn.wz.kid.wzkidexam.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.wz.kid.wzkidexam.entity.QuestionItem;
import cn.wz.kid.wzkidexam.utils.WzUtil;

public class WzQuestionLib {
    /** 题库. */
    private static Map<Integer, List<QuestionItem>> QUESTION_LIB = new HashMap<Integer, List<QuestionItem>>();
    /** 十以内加法. */
    private static List<QuestionItem> QUESTION_TYPE_0 = new ArrayList<QuestionItem>();
    /** 十以内减法. */
    private static List<QuestionItem> QUESTION_TYPE_1 = new ArrayList<QuestionItem>();
    /** 十以内加减法. */
    private static List<QuestionItem> QUESTION_TYPE_2 = new ArrayList<QuestionItem>();
    /** 二十以内加法. */
    private static List<QuestionItem> QUESTION_TYPE_3 = new ArrayList<QuestionItem>();
    /** 二十以内减法. */
    private static List<QuestionItem> QUESTION_TYPE_4 = new ArrayList<QuestionItem>();
    /** 二十以内加减法. */
    private static List<QuestionItem> QUESTION_TYPE_5 = new ArrayList<QuestionItem>();
    /** 乘法口诀. */
    private static List<QuestionItem> QUESTION_TYPE_6 = new ArrayList<QuestionItem>();
    /** 除法口诀. */
    private static List<QuestionItem> QUESTION_TYPE_7 = new ArrayList<QuestionItem>();

    /**
     * 构造方法.
     */
    public WzQuestionLib() {
        initQuestionType0();
        initQuestionType1();
        initQuestionType2();
        initQuestionType3();
        initQuestionType4();
        initQuestionType5();
        initQuestionType6();
        initQuestionType7();
        QUESTION_LIB.put(0, QUESTION_TYPE_0);
        QUESTION_LIB.put(1, QUESTION_TYPE_1);
        QUESTION_LIB.put(2, QUESTION_TYPE_2);
        QUESTION_LIB.put(3, QUESTION_TYPE_3);
        QUESTION_LIB.put(4, QUESTION_TYPE_4);
        QUESTION_LIB.put(5, QUESTION_TYPE_5);
        QUESTION_LIB.put(6, QUESTION_TYPE_6);
        QUESTION_LIB.put(7, QUESTION_TYPE_7);
    }

    /**
     * 获得题库考题.
     * @param type 题库类别
     * @return 题库考题列表
     */
    public List<QuestionItem> getExamQuestions(Integer type) {
        return QUESTION_LIB.get(type);
    }

    /*
     * 初始化各类题库.
     */
    private void initQuestionType0() {
        QuestionItem qi = null;
        Random rd = new Random();
        Integer[] showAnswers = null;
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                qi = new QuestionItem();
                qi.setQuestion(i + " + " + j + " =");
                int ans = i + j;
                qi.setTrueAnswer(ans);
                int trIdx = rd.nextInt(4);
                showAnswers = new Integer[] {-1, -1, -1, -1};
                for (int k = 0; k < 4; k++) {
                    if (k == trIdx) {
                        showAnswers[k] = ans;
                    } else {
                        while (true) {
                            int lblTmpAns = rd.nextInt(21);
                            if (WzUtil.contain(showAnswers, k, lblTmpAns) || ans == lblTmpAns) {
                                continue;
                            } else {
                                showAnswers[k] = lblTmpAns;
                                break;
                            }
                        }
                    }
                }
                qi.setLblAnswers(showAnswers);
                QUESTION_TYPE_0.add(qi);
            }
        }
    }
    private void initQuestionType1() {
        QuestionItem qi = null;
        Random rd = new Random();
        Integer[] showAnswers = null;
        for (int i = 10; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                qi = new QuestionItem();
                qi.setQuestion(i + " - " + j + " =");
                int ans = i - j;
                qi.setTrueAnswer(ans);
                int trIdx = rd.nextInt(4);
                showAnswers = new Integer[] {-1, -1, -1, -1};
                for (int k = 0; k < 4; k++) {
                    if (k == trIdx) {
                        showAnswers[k] = ans;
                    } else {
                        while (true) {
                            int lblTmpAns = rd.nextInt(21);
                            if (WzUtil.contain(showAnswers, k, lblTmpAns) || ans == lblTmpAns) {
                                continue;
                            } else {
                                showAnswers[k] = lblTmpAns;
                                break;
                            }
                        }
                    }
                }
                qi.setLblAnswers(showAnswers);
                QUESTION_TYPE_1.add(qi);
            }
        }
    }
    private void initQuestionType2() {
        QUESTION_TYPE_2.addAll(QUESTION_TYPE_0);
        QUESTION_TYPE_2.addAll(QUESTION_TYPE_1);
    }
    private void initQuestionType3() {
        QuestionItem qi = null;
        Random rd = new Random();
        Integer[] showAnswers = null;
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                qi = new QuestionItem();
                qi.setQuestion(i + " + " + j + " =");
                int ans = i + j;
                qi.setTrueAnswer(ans);
                int trIdx = rd.nextInt(4);
                showAnswers = new Integer[] {-1, -1, -1, -1};
                for (int k = 0; k < 4; k++) {
                    if (k == trIdx) {
                        showAnswers[k] = ans;
                    } else {
                        while (true) {
                            int lblTmpAns = rd.nextInt(21);
                            if (WzUtil.contain(showAnswers, k, lblTmpAns) || ans == lblTmpAns) {
                                continue;
                            } else {
                                showAnswers[k] = lblTmpAns;
                                break;
                            }
                        }
                    }
                }
                qi.setLblAnswers(showAnswers);
                QUESTION_TYPE_3.add(qi);
            }
        }
    }
    private void initQuestionType4() {
        QuestionItem qi = null;
        Random rd = new Random();
        Integer[] showAnswers = null;
        for (int i = 20; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                qi = new QuestionItem();
                qi.setQuestion(i + " - " + j + " =");
                int ans = i - j;
                qi.setTrueAnswer(ans);
                int trIdx = rd.nextInt(4);
                showAnswers = new Integer[] {-1, -1, -1, -1};
                for (int k = 0; k < 4; k++) {
                    if (k == trIdx) {
                        showAnswers[k] = ans;
                    } else {
                        while (true) {
                            int lblTmpAns = rd.nextInt(21);
                            if (WzUtil.contain(showAnswers, k, lblTmpAns) || ans == lblTmpAns) {
                                continue;
                            } else {
                                showAnswers[k] = lblTmpAns;
                                break;
                            }
                        }
                    }
                }
                qi.setLblAnswers(showAnswers);
                QUESTION_TYPE_4.add(qi);
            }
        }
    }
    private void initQuestionType5() {
        QUESTION_TYPE_5.addAll(QUESTION_TYPE_3);
        QUESTION_TYPE_5.addAll(QUESTION_TYPE_4);
    }
    private void initQuestionType6() {

    }
    private void initQuestionType7() {

    }
}
