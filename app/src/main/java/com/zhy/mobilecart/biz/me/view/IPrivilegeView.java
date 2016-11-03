package com.zhy.mobilecart.biz.me.view;

import com.zhy.mobilecart.biz.me.domain.Privileges;

import io.realm.RealmResults;

/**
 * <p/>
 * 创建时间: 16/10/31 上午12:31 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public interface IPrivilegeView {
    void showPrivileges(RealmResults<Privileges> privileges);
}
