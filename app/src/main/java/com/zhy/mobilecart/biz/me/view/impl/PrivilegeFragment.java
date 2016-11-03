package com.zhy.mobilecart.biz.me.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseFragment;
import com.zhy.mobilecart.base.recyclerview.CommonAdapter;
import com.zhy.mobilecart.base.recyclerview.base.ItemDivider;
import com.zhy.mobilecart.base.recyclerview.base.ViewHolder;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.me.presenter.PrivilegePresenter;
import com.zhy.mobilecart.biz.me.view.IPrivilegeView;
import com.zhy.mobilecart.biz.shoppingcart.domain.RefreshEvent;
import com.zhy.mobilecart.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.RealmResults;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * <p/>
 * 创建时间: 16/10/31 上午12:31 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class PrivilegeFragment extends BaseFragment implements IPrivilegeView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private List<Privileges> privileges = new ArrayList<>();


    private RecyclerView.Adapter adapter;

    private PrivilegePresenter presenter;

    @Override
    protected void initData() {

        presenter = new PrivilegePresenter(this);

        presenter.getPrivileges();

    }


    public static PrivilegeFragment newInstance() {
        Bundle args = new Bundle();
        PrivilegeFragment fragment = new PrivilegeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addItemDecoration(new ItemDivider(getContext(),
                LinearLayoutManager.VERTICAL, true));

        adapter = new CommonAdapter<Privileges>(getContext(), R.layout.list_item_privilege, privileges) {
            @Override
            protected void convert(ViewHolder holder, Privileges privileges, int position) {
                holder.setText(R.id.tv_name, String.format("满%s减%s优惠券", privileges.getConsumeAmount(), privileges.getCreditAmount()));
            }
        };

        recyclerView.setAdapter(adapter);

        Observable<RefreshEvent> observable = RxBus.getInstance().register(this);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RefreshEvent>() {
                    @Override
                    public void call(RefreshEvent event) {
                        if (event.getType() == RefreshEvent.refresh_privilege)
                            presenter.getPrivileges();
                    }
                });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discount;
    }


    @Override
    public void showPrivileges(RealmResults<Privileges> privileges) {

        this.privileges.clear();
        this.privileges.addAll(privileges);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RxBus.getInstance().unregister(this);

    }

    @OnClick(R.id.rl_add_discount)
    public void addDiscountEvent(View view){
        Intent intent = new Intent(getContext(),AddPrivilegeActivity.class);
        context.startActivity(intent);
    }
}
