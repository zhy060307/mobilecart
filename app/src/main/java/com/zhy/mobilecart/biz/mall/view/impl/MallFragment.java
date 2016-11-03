package com.zhy.mobilecart.biz.mall.view.impl;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseFragment;
import com.zhy.mobilecart.biz.mall.domin.GoodsType;
import com.zhy.mobilecart.biz.mall.presenter.MallPresenter;
import com.zhy.mobilecart.biz.mall.view.IMallView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商场页面
 * <p/>
 * 创建时间: 16/10/30 下午5:22 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class MallFragment extends BaseFragment implements IMallView {


    private List<GoodsType> goodsTypeList = new ArrayList<>();

    private List<GoodsFragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @BindView(R.id.tl_view)
    TabLayout tabLayout;

    @BindView(R.id.vp_view)
    ViewPager viewPager;
    private GoodsFragmentPagerAdapter pagerAdapter;

    private MallPresenter mallPresenter;

    @Override
    protected void initData() {

        mallPresenter = new MallPresenter(this, getContext());

        mallPresenter.getAllGoodsType();

    }

    @Override
    protected void initView(View rootView) {
        pagerAdapter = new GoodsFragmentPagerAdapter(getFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void showGoodsType(List<GoodsType> allGoodsType) {

        this.goodsTypeList = allGoodsType;
        refreshGoodsType();
    }


    private class GoodsFragmentPagerAdapter extends FragmentPagerAdapter {

        public GoodsFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }



    private void refreshGoodsType() {
        if (this.goodsTypeList != null) {
            for (GoodsType type : goodsTypeList) {
                fragmentList.add(GoodsFragment.newInstance(type.getId()));
                titles.add(type.getName());
            }
        }
        pagerAdapter.notifyDataSetChanged();
    }
}
