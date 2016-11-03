package com.zhy.mobilecart.biz.me.presenter;

import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.model.DiscountModel;
import com.zhy.mobilecart.biz.me.view.IDiscountView;

import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * <p/>
 * 创建时间: 16/10/31 上午12:06 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class DiscountPresenter {

    private IDiscountView discountView;

    private DiscountModel model;

    public DiscountPresenter(IDiscountView discountView) {
        this.discountView = discountView;
        this.model = new DiscountModel();
    }

    public void getDiscounts() {

        this.model.queryDiscounts().subscribe(new Action1<RealmResults<Discount>>() {
            @Override
            public void call(RealmResults<Discount> discounts) {

                discountView.showDiscounts(discounts);
            }
        });
    }

    public void addDiscount(Discount discount) {
        model.addDiscount(discount);
    }
}
