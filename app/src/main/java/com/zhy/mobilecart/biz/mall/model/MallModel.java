package com.zhy.mobilecart.biz.mall.model;

import android.content.Context;

import com.zhy.mobilecart.biz.mall.domin.GoodsType;
import com.zhy.mobilecart.manager.RealmManger;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * <p/>
 * 创建时间: 16/10/30 下午7:30 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class MallModel {


    private Context context;

    private RealmManger realmManger;

    public MallModel(Context context) {
        this.context = context;

        realmManger = RealmManger.getInstance();
    }

    public Observable<RealmResults<GoodsType>> getAllGoodsType() {

        Realm realm = Realm.getDefaultInstance();
        Observable<RealmResults<GoodsType>> observable =
                realm.where(GoodsType.class).findAllSortedAsync("id").asObservable();

        return observable;

    }
}
