package com.zhy.mobilecart.biz.main.domian;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.biz.mall.view.impl.MallFragment;
import com.zhy.mobilecart.biz.me.view.impl.MeFragment;
import com.zhy.mobilecart.biz.shoppingcart.view.impl.ShoppingCartFragment;

/**
 * <p/>
 * 创建时间: 16/10/30 下午4:54 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public enum MainTabView {


    MALL(0, R.string.mall, R.drawable.selector_main_tab_mall, MallFragment.class),
    ME(1, R.string.me, R.drawable.selector_main_tab_me, MeFragment.class),
    SHOPPING_CART(2, R.string.shopping_cart, R.drawable.selector_main_tab_cart, ShoppingCartFragment.class);


    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    MainTabView(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }


    public int getResName() {
        return resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }
}


