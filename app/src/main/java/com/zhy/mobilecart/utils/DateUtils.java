package com.zhy.mobilecart.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p/>
 * 创建时间: 16/10/30 下午10:59 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class DateUtils {

    public static String format_yyyy_MM_dd_EN = "yyyy.MM.dd";

    public static Date getStringToDate(String stringValue) {
        SimpleDateFormat StringToDate = new SimpleDateFormat(format_yyyy_MM_dd_EN);
        Date returnDate = new Date();
        try {
            returnDate = StringToDate.parse(stringValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnDate;


    }


    public static String getDateToString(Date dateIn) {
        SimpleDateFormat DateToString = new SimpleDateFormat(format_yyyy_MM_dd_EN);
        return DateToString.format(dateIn);
    }

    public static boolean isBigThan(Date d1, Date d2) {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd"); //格式化为 hhmmss
        int d1Number = Integer.parseInt(f.format(d1).toString()); //将第一个时间格式化后转为int
        int d2Number = Integer.parseInt(f.format(d2).toString()); //将第二个时间格式化后转为int
        return d1Number >= d2Number;
    }
}
