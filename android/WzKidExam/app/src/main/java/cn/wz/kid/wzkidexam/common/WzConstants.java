package cn.wz.kid.wzkidexam.common;

import java.util.HashMap;
import java.util.Map;

public class WzConstants {
    /** 题库类别. */
    public static final Map<Integer, String> QUESTION_TYPE_MAP = new HashMap<Integer, String>() {{
        put(0, "十以内加法");
        put(1, "十以内减法");
        put(2, "十以内加减法");
        put(3, "二十以内加法");
        put(4, "二十以内减法");
        put(5, "二十以内加减法");
        put(6, "乘法口诀");
        put(7, "除法口诀");
    }};
}
