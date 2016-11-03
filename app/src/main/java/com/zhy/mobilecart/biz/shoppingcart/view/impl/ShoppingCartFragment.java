package com.zhy.mobilecart.biz.shoppingcart.view.impl;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseFragment;
import com.zhy.mobilecart.base.recyclerview.CommonAdapter;
import com.zhy.mobilecart.base.recyclerview.base.ItemDivider;
import com.zhy.mobilecart.base.recyclerview.base.ViewHolder;
import com.zhy.mobilecart.base.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;
import com.zhy.mobilecart.biz.shoppingcart.domain.RefreshEvent;
import com.zhy.mobilecart.biz.shoppingcart.model.GoodsCalculator;
import com.zhy.mobilecart.biz.shoppingcart.presenter.ShoppingCartPresenter;
import com.zhy.mobilecart.biz.shoppingcart.view.IShoppingCartView;
import com.zhy.mobilecart.utils.ListUtils;
import com.zhy.mobilecart.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 购物车
 * <p/>
 * 创建时间: 16/10/30 下午9:39 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class ShoppingCartFragment extends BaseFragment implements IShoppingCartView {


    @BindView(R.id.rc_view)
    RecyclerView recyclerView;


    private RecyclerView.Adapter adapter;

    private List<ChooseGoods> datas = new ArrayList<>();


    private ShoppingCartPresenter presenter;
    private HeaderAndFooterWrapper wrapper;
    private TotalConsumeView totalConsumeView;

    @Override
    protected void initData() {
        presenter = new ShoppingCartPresenter(this);
        presenter.queryChooseGoods();
    }

    @Override
    protected void initView(View rootView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDivider(getContext(), LinearLayoutManager.VERTICAL, true));


        adapter = new CommonAdapter<ChooseGoods>(getContext(), R.layout.list_item_goods, datas) {
            @Override
            protected void convert(ViewHolder holder, final ChooseGoods goodsItem, int position) {

                holder.getView(R.id.iv_add_goods).setVisibility(View.GONE);
                holder.getView(R.id.ll_count).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_name, goodsItem.getGoodsName())
                        .setText(R.id.tv_price, "￥" + goodsItem.getGoodsPrice())
                        .setEditText(R.id.et_count, goodsItem.getCount() + "")
                ;

                EditText editText = holder.getView(R.id.et_count);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        if (!TextUtils.isEmpty(s)) {
                            int num = Integer.parseInt(s.toString());
//                            updateGoods(goodsItem,num);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
        };


        wrapper = new HeaderAndFooterWrapper(adapter);
        totalConsumeView = new TotalConsumeView(getContext());
        wrapper.addFootView(totalConsumeView);
        recyclerView.setAdapter(wrapper);


        Observable<RefreshEvent> observable = RxBus.getInstance().register(this);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RefreshEvent>() {
                    @Override
                    public void call(RefreshEvent event) {
                        if (event.getType() == RefreshEvent.refresh_cart)
                            presenter.queryChooseGoods();
                    }
                });


    }

    private void updateGoods(ChooseGoods goodsItem, int num) {
        presenter.saveGoods(goodsItem.getGoodsId(), num);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    public void showChooseGoodses(List<ChooseGoods> chooseGoodses) {
        this.datas.clear();
        this.datas.addAll(chooseGoodses);
        wrapper.notifyDataSetChanged();

        if (ListUtils.isNotEmpty(chooseGoodses)) {
            presenter.calculate();
        }
    }

    @Override
    public void showResult(GoodsCalculator.CalculateResult result) {
        if (null != totalConsumeView) {
            totalConsumeView.showView(result);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RxBus.getInstance().unregister(this);

    }
}
