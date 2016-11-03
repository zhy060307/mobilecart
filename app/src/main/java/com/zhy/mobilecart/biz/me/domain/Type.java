package com.zhy.mobilecart.biz.me.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * 创建时间: 16/10/31 上午12:13 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public enum Type {

    ELECTRONIC(0, "电子"),
    FOOD(1, "食品"),
    NECESSARY(2, "日用品"),
    BEER(3, "酒类");


    Type(int index, String name) {
        this.index = index;
        this.name = name;
    }

    private int index;
    private String name;


    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public static Type typeOf(int index) {


        for (Type type : values()) {
            if (index == type.index) {
                return type;
            }
        }

        throw new RuntimeException("no type matches!");
    }

    public static List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Type type : values()) {
            names.add(type.name);
        }
        return names;

    }
}
