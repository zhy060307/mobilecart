package com.zhy.mobilecart.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.manager.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p/>
 * 创建时间: 16/10/30 下午4:32 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class BaseActivity extends AppCompatActivity {

    private static final int BASE_LAYOUT_RES_ID = R.layout.activity_base;
    private static final LinearLayout.LayoutParams LAYOUT_PARAMS =
            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

    private LinearLayout parentView;
    private Toolbar toolbar;
    private TextView textViewTitle;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(BASE_LAYOUT_RES_ID);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        init();
    }

    private void init() {
        initToolBar();
        AppManager.getInstance().addActivity(this);
    }

    private void initToolBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationIcon(R.drawable.button_back_red);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != unbinder) {
            unbinder.unbind();
        }
        AppManager.getInstance().finishActivity(this);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (BASE_LAYOUT_RES_ID == layoutResID) {
            super.setContentView(layoutResID);
            parentView = (LinearLayout) findViewById(R.id.base_parent_view);
            toolbar = (Toolbar) findViewById(R.id.toolbar_view);
            textViewTitle = (TextView) findViewById(R.id.tv_title);
        } else {
            parentView.addView(getLayoutInflater().inflate(layoutResID, null), LAYOUT_PARAMS);
            unbinder = ButterKnife.bind(this);
        }
    }

    protected void goBack() {
        AppManager.getInstance().finishActivity(this);
    }


    public void setViewTitle(CharSequence title) {
        if (null != textViewTitle) {
            textViewTitle.setText(title);
        }
    }

    protected void setViewTitle(@StringRes int resId) {
        if (null != textViewTitle) {
            textViewTitle.setText(resId);

        }
    }

    public void setViewTitle(CharSequence title, int color) {
        if (null != textViewTitle) {
            textViewTitle.setText(title);
            textViewTitle.setTextColor(color);
        }
    }


    public void setToolbarVisible(boolean isVisible) {
        toolbar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
