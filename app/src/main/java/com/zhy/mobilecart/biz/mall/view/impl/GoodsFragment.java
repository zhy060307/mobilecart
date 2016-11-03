package com.zhy.mobilecart.biz.mall.view.impl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseFragment;
import com.zhy.mobilecart.base.recyclerview.CommonAdapter;
import com.zhy.mobilecart.base.recyclerview.base.ItemDivider;
import com.zhy.mobilecart.base.recyclerview.base.ViewHolder;
import com.zhy.mobilecart.biz.mall.domin.GoodsItem;
import com.zhy.mobilecart.biz.mall.presenter.GoodsPresenter;
import com.zhy.mobilecart.biz.mall.view.IGoodsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商品页面
 * <p/>
 * 创建时间: 16/10/30 下午5:37 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class GoodsFragment extends BaseFragment implements IGoodsView {

    public static final String ARGS_GOODS_TYPE = "args_goods_type";

    private int goodsType;

    @BindView(R.id.rc_view)
    RecyclerView recyclerView;


    private RecyclerView.Adapter adapter;

    private GoodsPresenter presenter;


    private List<GoodsItem> datas = new ArrayList<>();


    @Override
    protected void initData() {
        goodsType = getArguments().getInt(ARGS_GOODS_TYPE);

        presenter = new GoodsPresenter(this, getContext());
        presenter.getGoodsListByType(goodsType);


    }

    @Override
    protected void initView(View rootView) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDivider(getContext(), LinearLayoutManager.VERTICAL, true));
        adapter = new CommonAdapter<GoodsItem>(getContext(), R.layout.list_item_goods, datas) {
            @Override
            protected void convert(ViewHolder holder, final GoodsItem goodsItem, int position) {

                holder.setText(R.id.tv_name, goodsItem.getName())
                        .setText(R.id.tv_price, "￥" + goodsItem.getPrice());

                holder.getView(R.id.iv_add_goods).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        presenter.addGoods(goodsItem);
                    }
                });

            }
        };

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods;
    }


    public static GoodsFragment newInstance(int goodsType) {
        Bundle args = new Bundle();

        args.putInt(ARGS_GOODS_TYPE, goodsType);
        GoodsFragment fragment = new GoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showGoods(List<GoodsItem> itemList) {
        this.datas.clear();
        this.datas.addAll(itemList);

        adapter.notifyDataSetChanged();
    }
}
