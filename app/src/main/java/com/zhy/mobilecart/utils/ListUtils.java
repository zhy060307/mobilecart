package com.zhy.mobilecart.utils;

import java.util.List;

/**
 * ListUtils
 * <p/>
 * 创建时间: 15/10/27 上午11:08 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */
public class ListUtils {

    /**
     * 判断集合是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List<? extends Object> list) {
        if (null != list && !list.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 判断集合不为空
     *
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List<? extends Object> list) {
        return !isEmpty(list);

    }


}
