package com.zhy.mobilecart.biz.me.presenter;

import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.me.model.PrivilegeModel;
import com.zhy.mobilecart.biz.me.view.IPrivilegeView;

import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * <p/>
 * 创建时间: 16/10/31 上午12:33 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class PrivilegePresenter {

    private IPrivilegeView privilegeView;
    private PrivilegeModel model;

    public PrivilegePresenter(IPrivilegeView privilegeView) {
        this.privilegeView = privilegeView;
        this.model = new PrivilegeModel();
    }


    public void getPrivileges() {

        this.model.queryPrivileges().subscribe(new Action1<RealmResults<Privileges>>() {
            @Override
            public void call(RealmResults<Privileges> privileges) {

                privilegeView.showPrivileges(privileges);
            }
        });

    }

    public void addPrivilege(Privileges privileges) {
        this.model.addPrivilege(privileges);
    }
}
