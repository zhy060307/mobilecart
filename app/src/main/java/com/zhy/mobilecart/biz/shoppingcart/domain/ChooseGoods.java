package com.zhy.mobilecart.biz.shoppingcart.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 选择的商品
 * <p/>
 * 创建时间: 16/10/31 上午12:55 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class ChooseGoods extends RealmObject {

    @PrimaryKey
    private int goodsId;

    private int count;
    private int goodsType;
    private double goodsPrice;
    private String goodsName;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public ChooseGoods() {
    }

    public ChooseGoods(int count, int goodsId, int goodsType, double goodsPrice, String goodsName) {
        this.count = count;
        this.goodsId = goodsId;
        this.goodsType = goodsType;
        this.goodsPrice = goodsPrice;
        this.goodsName = goodsName;
    }
}
