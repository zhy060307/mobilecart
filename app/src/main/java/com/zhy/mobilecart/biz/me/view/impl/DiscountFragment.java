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
import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Type;
import com.zhy.mobilecart.biz.me.presenter.DiscountPresenter;
import com.zhy.mobilecart.biz.me.view.IDiscountView;
import com.zhy.mobilecart.biz.shoppingcart.domain.RefreshEvent;
import com.zhy.mobilecart.utils.DateUtils;
import com.zhy.mobilecart.utils.RxBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * <p/>
 * 创建时间: 16/10/30 下午11:13 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class DiscountFragment extends BaseFragment implements IDiscountView {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;




    private List<Discount> discounts = new ArrayList<>();


    private RecyclerView.Adapter adapter;

    private DiscountPresenter presenter;

    @Override
    protected void initData() {

        presenter = new DiscountPresenter(this);

        presenter.getDiscounts();

    }


    public static DiscountFragment newInstance() {
        Bundle args = new Bundle();
        DiscountFragment fragment = new DiscountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addItemDecoration(new ItemDivider(getContext(),
                LinearLayoutManager.VERTICAL, true));

        adapter = new CommonAdapter<Discount>(getContext(), R.layout.list_item_discount, discounts) {
            @Override
            protected void convert(ViewHolder holder, Discount discount, int position) {

                holder.setText(R.id.tv_name, Type.typeOf(discount.getType()).getName() + "类")
                        .setText(R.id.tv_expirationTime, "截止日期:" + DateUtils.getDateToString(new Date(discount.getExpirationTime())))
                        .setText(R.id.tv_discount, discount.getDiscount() + "折");
            }

        };

        recyclerView.setAdapter(adapter);


        Observable<RefreshEvent> observable = RxBus.getInstance().register(this);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RefreshEvent>() {
                    @Override
                    public void call(RefreshEvent event) {
                        if (event.getType() == RefreshEvent.refresh_discount)
                            presenter.getDiscounts();;
                    }
                });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discount;
    }

    @Override
    public void showDiscounts(List<Discount> discounts) {
        this.discounts.clear();
        this.discounts.addAll(discounts);

        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.rl_add_discount)
    public void addDiscountEvent(View view){
        Intent intent = new Intent(getContext(),AddDiscountActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RxBus.getInstance().unregister(this);

    }
}
