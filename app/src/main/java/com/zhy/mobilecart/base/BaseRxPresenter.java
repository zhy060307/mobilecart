package com.zhy.mobilecart.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lenovo on 2016/11/2.
 */

public abstract class BaseRxPresenter {

    protected IBaseView view;
    protected CompositeSubscription compositeSubscription;

    protected void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }


    public void attachView(IBaseView view) {
        this.view = view;
    }


    public void detachView() {
        this.view = null;
        unSubscribe();
    }
}
