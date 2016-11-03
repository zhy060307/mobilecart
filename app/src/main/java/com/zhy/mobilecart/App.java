package com.zhy.mobilecart;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * <p/>
 * 创建时间: 16/10/30 下午8:08 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class App extends Application {

    private static final String DB_NAME = "mobile_cart.realm";

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration
                .Builder().deleteRealmIfMigrationNeeded()
                .name(DB_NAME).build();
        Realm.setDefaultConfiguration(configuration);

    }
}
