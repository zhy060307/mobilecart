package com.zhy.mobilecart.biz.me.model;

import com.zhy.mobilecart.biz.me.domain.Discount;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * 创建时间: 16/10/31 上午12:06 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class DiscountModel {

    public Observable<RealmResults<Discount>> queryDiscounts() {

        Realm mRealm = Realm.getDefaultInstance();
        Observable<RealmResults<Discount>> observable =
                mRealm.where(Discount.class)
                        .findAll()
                        .asObservable();
        return observable;
    }

    public void addDiscount(Discount discount) {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        mRealm.copyToRealm(discount);
        mRealm.commitTransaction();

    }
}
