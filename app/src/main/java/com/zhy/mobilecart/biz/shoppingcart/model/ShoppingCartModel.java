package com.zhy.mobilecart.biz.shoppingcart.model;

import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * TODO 功能描述
 * <p/>
 * 创建时间: 16/10/31 上午1:09 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class ShoppingCartModel {

    public Observable<RealmResults<ChooseGoods>> queryChooseGoods() {

        Realm mRealm = Realm.getDefaultInstance();
        Observable<RealmResults<ChooseGoods>> observable =
                mRealm.where(ChooseGoods.class)
                        .findAllAsync()
                        .asObservable();
        return observable;
    }

    public List<Discount> queryDiscounts() {
        Realm mRealm = Realm.getDefaultInstance();
        return mRealm.where(Discount.class).findAll();
    }

    public List<Privileges> queryPrivileges() {
        Realm mRealm = Realm.getDefaultInstance();
        return mRealm.where(Privileges.class).findAll();
    }

    public List<ChooseGoods> queryChooseGoodees() {
        Realm mRealm = Realm.getDefaultInstance();
        return mRealm.where(ChooseGoods.class).findAll();
    }

    public void saveGoods(final int goodsId, final int num) {
        Realm realRealm = Realm.getDefaultInstance();

        realRealm.beginTransaction();
        realRealm.where(ChooseGoods.class).equalTo("goodsId", goodsId).findFirst().setCount(num);

        realRealm.commitTransaction();
    }
}
