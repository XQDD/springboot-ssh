package ${groupId}.utils;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommUtils {
    //年月日期格式化
    public final static SimpleDateFormat DATE_YEAR_MOUTH_FORMAT = new SimpleDateFormat("yyyyMM");
    //普通日期格式化
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    //精确到微秒日期时间格式化
    public final static SimpleDateFormat DATETIME_FORMAT_WITH_MS = new SimpleDateFormat("yyyy年MM月dd号H时m分s.sss秒");

    /**
     * 字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * 数组是否为空
     *
     * @param o
     * @return
     */
    public static boolean isBlank(Object o[]) {
        return o == null || o.length == 0;
    }

    /**
     * 列表是否为空
     *
     * @param o
     * @return
     */
    public static boolean isBlank(List o) {
        return o == null || o.isEmpty();
    }





}
