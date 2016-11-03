package com.zhy.mobilecart.biz.me.view.impl;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p/>
 * 创建时间: 16/10/30 下午10:31 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class MeFragment extends BaseFragment {


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @BindView(R.id.tab_view)
    TabLayout tabLayout;

    @BindView(R.id.vipe_view)
    ViewPager viewPager;
    private OffersFragmentPagerAdapter pagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View rootView) {
        titles.add("折扣");
        titles.add("优惠券");


        fragmentList.add(DiscountFragment.newInstance());
        fragmentList.add(PrivilegeFragment.newInstance());

        pagerAdapter = new OffersFragmentPagerAdapter(getFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    private class OffersFragmentPagerAdapter extends FragmentPagerAdapter {

        public OffersFragmentPagerAdapter(FragmentManager fm) {
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


}
