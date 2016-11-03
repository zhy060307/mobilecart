package com.zhy.mobilecart.biz.mall.presenter;

import android.content.Context;

import com.zhy.mobilecart.biz.mall.domin.GoodsItem;
import com.zhy.mobilecart.biz.mall.model.GoodsModel;
import com.zhy.mobilecart.biz.mall.view.IGoodsView;

import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * <p/>
 * 创建时间: 16/10/30 下午7:49 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class GoodsPresenter {

    private IGoodsView goodsView;

    private GoodsModel goodsModel;

    public GoodsPresenter(IGoodsView goodsView, Context context) {
        this.goodsView = goodsView;
        this.goodsModel = new GoodsModel();
    }

    public void getGoodsListByType(int goodsType) {
        this.goodsModel.queryGoodsByType(goodsType).subscribe(new Action1<RealmResults<GoodsItem>>() {
            @Override
            public void call(RealmResults<GoodsItem> goodsItems) {
                goodsView.showGoods(goodsItems);
            }
        });


    }

    public void addGoods(GoodsItem goodsItem) {
        this.goodsModel.addGoods(goodsItem);


    }
}
