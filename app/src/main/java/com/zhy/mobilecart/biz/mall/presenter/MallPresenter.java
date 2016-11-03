package com.zhy.mobilecart.biz.mall.presenter;

import android.content.Context;

import com.zhy.mobilecart.biz.mall.domin.GoodsType;
import com.zhy.mobilecart.biz.mall.model.MallModel;
import com.zhy.mobilecart.biz.mall.view.IMallView;

import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * <p/>
 * 创建时间: 16/10/30 下午7:29 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class MallPresenter {

    private IMallView mallView;

    private Context context;

    private MallModel mallModel;


    public MallPresenter(IMallView mallView, Context context) {
        this.mallView = mallView;
        this.context = context;
        this.mallModel = new MallModel(context);
    }


    public void getAllGoodsType() {

        mallModel.getAllGoodsType()
                .filter(new Func1<RealmResults<GoodsType>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<GoodsType> goodsTypes) {
                        return goodsTypes.isLoaded();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RealmResults<GoodsType>>() {
                    @Override
                    public void call(RealmResults<GoodsType> goodsTypes) {
                        mallView.showGoodsType(goodsTypes);
                    }
                });

    }

}
