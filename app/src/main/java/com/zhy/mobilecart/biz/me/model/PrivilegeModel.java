package com.zhy.mobilecart.biz.me.model;

import com.zhy.mobilecart.biz.me.domain.Privileges;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * <p/>
 * 创建时间: 16/10/31 上午12:33 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class PrivilegeModel {

    public Observable<RealmResults<Privileges>> queryPrivileges() {

        Realm mRealm = Realm.getDefaultInstance();
        Observable<RealmResults<Privileges>> observable =
                mRealm.where(Privileges.class)
                        .findAll()
                        .asObservable();
        return observable;
    }

    public void addPrivilege(Privileges privileges) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(privileges);
        realm.commitTransaction();
    }
}
