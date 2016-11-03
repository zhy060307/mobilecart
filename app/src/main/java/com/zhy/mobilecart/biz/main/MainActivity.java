package com.zhy.mobilecart.biz.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseActivity;
import com.zhy.mobilecart.biz.main.domian.MainTabView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_tab_view)
    FragmentTabHost mainTab;


    private MainTabView[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbarVisible(false);
        initView();

    }

    private void initView() {
        mainTab.setup(this, getSupportFragmentManager(), R.id.main_content_view);
        mainTab.getTabWidget().setShowDividers(0);
        initTab();
    }

    private void initTab() {

        tabs = MainTabView.values();
        for (int i = 0; i < tabs.length; i++) {
            MainTabView tabView = tabs[i];
            TabHost.TabSpec tab = mainTab.newTabSpec(getString(tabView.getResName()));

            View indicator = View.inflate(this, R.layout.item_main_tab, null);

            ImageView iconView = (ImageView) indicator.findViewById(R.id.iv_icon);
            TextView titleView = (TextView) indicator.findViewById(R.id.tv_title);

            iconView.setImageResource(tabView.getResIcon());
            titleView.setText(tabView.getResName());

            tab.setIndicator(indicator);
            mainTab.addTab(tab, tabView.getClz(), null);


        }
    }


}
