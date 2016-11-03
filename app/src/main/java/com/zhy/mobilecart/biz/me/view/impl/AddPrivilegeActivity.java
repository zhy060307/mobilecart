package com.zhy.mobilecart.biz.me.view.impl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseActivity;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.me.presenter.PrivilegePresenter;
import com.zhy.mobilecart.biz.me.view.IPrivilegeView;
import com.zhy.mobilecart.biz.shoppingcart.domain.RefreshEvent;
import com.zhy.mobilecart.manager.AppManager;
import com.zhy.mobilecart.utils.RxBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.RealmResults;

/**
 * Created by lenovo on 2016/11/3.
 */

public class AddPrivilegeActivity extends BaseActivity implements IPrivilegeView {


    @BindView(R.id.et_consumeAmount)
    EditText consumeAmountEt;

    @BindView(R.id.et_creditAmount)
    EditText creditAmountEt;

    @BindView(R.id.tv_date)
    TextView dateTextView;




    private Calendar calendar = Calendar.getInstance();

    private PrivilegePresenter discountPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_privileage);
        setViewTitle(R.string.add_privilege);


        discountPresenter = new PrivilegePresenter(this);
    }


    @OnClick(R.id.ll_date_picker)
    public void onDatePicker(View view) {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                        dateTextView.setText(year + "年" + (month + 1) + "月" + dayOfMonth + "日");


                        calendar.set(Calendar.YEAR, year); // 传入年份
                        calendar.set(Calendar.MONTH, month + 1);// 传入月份
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);// 传入天数
                    }
                },
                calendar.get(Calendar.YEAR), // 传入年份
                calendar.get(Calendar.MONTH), // 传入月份
                calendar.get(Calendar.DAY_OF_MONTH) // 传入天数
        );

        dialog.show();
    }


    @OnClick(R.id.rl_add_ok)
    public void onOk(View view) {
        if (!TextUtils.isEmpty(dateTextView.getText().toString())
                &&!TextUtils.isEmpty(consumeAmountEt.getText().toString())
                &&!TextUtils.isEmpty(creditAmountEt.getText().toString())) {

            Privileges privileges = new Privileges();
            privileges.setConsumeAmount(Double.parseDouble(consumeAmountEt.getText().toString()));
            privileges.setCreditAmount(Double.parseDouble(creditAmountEt.getText().toString()));
            privileges.setExpirationTime(calendar.getTimeInMillis());

            discountPresenter.addPrivilege(privileges);
            AppManager.getInstance().finishActivity();

            RxBus.getInstance().post(new RefreshEvent(RefreshEvent.refresh_privilege));

        }
    }


    @Override
    public void showPrivileges(RealmResults<Privileges> privileges) {

    }
}
