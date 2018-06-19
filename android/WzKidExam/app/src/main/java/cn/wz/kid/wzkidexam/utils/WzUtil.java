package cn.wz.kid.wzkidexam.utils;

import java.util.List;

public class WzUtil {
    /**
     * 是否在数组中存在.
     * @param ls 数组
     * @param lastIdx 需比对最后的Idx
     * @param num 本次的值
     * @return true为已经存在
     */
    public static boolean contain(Integer[] ls, int lastIdx, Integer num) {
        for (int m = 0; m < lastIdx; m++) {
            if (ls[m].intValue() == num.intValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否在列表中存在.
     * @param ls 列表
     * @param num 本次的值
     * @return true为已经存在
     */
    public static boolean contain(List<Integer> ls, Integer num) {
        for (int m = 0; m < ls.size(); m++) {
            if (ls.get(m).intValue() == num.intValue()) {
                return true;
            }
        }
        return false;
    }
}
