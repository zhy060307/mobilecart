package com.zhy.mobilecart.biz.me.domain;

import io.realm.RealmObject;

/**
 * <p/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class Discount extends RealmObject {

    private int id;
    private int type;
    private double discount;
    private long expirationTime;


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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Discount() {
    }

    public Discount(int id, int type, double discount, long expirationTime) {
        this.id = id;
        this.type = type;
        this.discount = discount;
        this.expirationTime = expirationTime;
    }
}
