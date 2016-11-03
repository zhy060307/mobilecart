package com.zhy.mobilecart.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p/>
 * 创建时间: 16/7/6 上午10:39 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */
public abstract class BaseFragment extends Fragment {

    private boolean isDestroy;

    protected LayoutInflater inflater;

    protected View rootView;

    protected Context context;

    //执行逻辑管理task
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.isDestroy = false;
        this.context = getContext();

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        } else {
            rootView = inflater.inflate(getLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
            initView(rootView);
            initData();
        }
        return rootView;
    }


    /**
     *
     */
    protected abstract void initData();

    /**
     * @param rootView
     */
    protected abstract void initView(View rootView);

    /**
     * @return
     */
    protected abstract int getLayoutId();


    private String getTAG() {
        return this.getClass().getSimpleName();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.isDestroy = true;
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


}
