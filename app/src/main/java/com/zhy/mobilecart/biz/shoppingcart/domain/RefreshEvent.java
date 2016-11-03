package com.zhy.mobilecart.biz.shoppingcart.domain;

/**
 * TODO 功能描述
 * <p/>
 * 创建时间: 16/10/31 上午1:36 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class RefreshEvent {

    public static final int refresh_cart = 0;
    public static final int refresh_discount = 1;
    public static final int refresh_privilege = 2;

    private int type;

    public RefreshEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
