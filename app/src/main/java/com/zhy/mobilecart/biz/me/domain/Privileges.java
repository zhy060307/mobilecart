package com.zhy.mobilecart.biz.me.domain;

import io.realm.RealmObject;

/**
 * 优惠券
 * <p/>
 * 创建时间: 16/10/30 下午10:38 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class Privileges extends RealmObject {

    private int id;
    private double consumeAmount;
    private double creditAmount;
    private long expirationTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }


    public Privileges() {
    }

    public Privileges(int id, double consumeAmount, double creditAmount, long expirationTime) {
        this.id = id;
        this.consumeAmount = consumeAmount;
        this.creditAmount = creditAmount;
        this.expirationTime = expirationTime;
    }
}
