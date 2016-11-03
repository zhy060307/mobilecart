package com.zhy.mobilecart.biz.mall.domin;

import io.realm.RealmObject;

/**
 * <p/>
 * 创建时间: 16/10/30 下午6:53 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class GoodsItem extends RealmObject{

    private int id;
    private int type;
    private double price;
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
