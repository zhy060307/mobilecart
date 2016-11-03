package com.zhy.mobilecart.biz.mall.model;

import com.zhy.mobilecart.biz.mall.domin.GoodsItem;
import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;
import com.zhy.mobilecart.biz.shoppingcart.domain.RefreshEvent;
import com.zhy.mobilecart.manager.RealmManger;
import com.zhy.mobilecart.utils.RxBus;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * <p/>
 * 创建时间: 16/10/30 下午7:50 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class GoodsModel {


    private RealmManger realmManger;

    public GoodsModel() {
        realmManger = RealmManger.getInstance();
    }

    public Observable<RealmResults<GoodsItem>> queryGoodsByType(int goodsType) {

        Realm mRealm = Realm.getDefaultInstance();
        Observable<RealmResults<GoodsItem>> observable =
                mRealm.where(GoodsItem.class)
                        .equalTo("type", goodsType)
                        .findAll()
                        .asObservable();
        return observable;
    }

    public void addGoods(GoodsItem goodsItem) {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();

        RealmResults<ChooseGoods> chooseGoodses =
                mRealm.where(ChooseGoods.class)
                        .equalTo("goodsId", goodsItem.getId())
                        .findAll();
        if (chooseGoodses == null || chooseGoodses.size() == 0) {
            ChooseGoods chooseGoods = new ChooseGoods();
            chooseGoods.setCount(1);
            chooseGoods.setGoodsId(goodsItem.getId());
            chooseGoods.setGoodsName(goodsItem.getName());
            chooseGoods.setGoodsPrice(goodsItem.getPrice());
            chooseGoods.setGoodsType(goodsItem.getType());
            mRealm.insertOrUpdate(chooseGoods);

        } else {
            for (ChooseGoods chooseGoodse : chooseGoodses) {
                if (chooseGoodse.getGoodsId() == goodsItem.getId()) {
                    chooseGoodse.setCount(chooseGoodse.getCount() + 1);
                    break;
                }
            }

        }


        mRealm.commitTransaction();
        RxBus.getInstance().post(new RefreshEvent(0));



    }
}
