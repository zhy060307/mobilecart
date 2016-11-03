package com.zhy.mobilecart.biz.mall.domin;

import io.realm.RealmObject;

/**
 * <p/>
 * 创建时间: 16/10/30 下午6:53 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class GoodsType extends RealmObject {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
