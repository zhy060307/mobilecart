package com.zhy.mobilecart.biz.me.view;

import com.zhy.mobilecart.biz.me.domain.Discount;

import java.util.List;

/**
 * TODO 功能描述
 * <p/>
 * 创建时间: 16/10/30 下午11:12 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public interface IDiscountView {
    void showDiscounts(List<Discount> discounts);
}
