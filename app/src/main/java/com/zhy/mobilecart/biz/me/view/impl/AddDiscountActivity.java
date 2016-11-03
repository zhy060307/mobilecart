package com.zhy.mobilecart.biz.me.view.impl;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.base.BaseActivity;
import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Type;
import com.zhy.mobilecart.biz.me.presenter.DiscountPresenter;
import com.zhy.mobilecart.biz.me.view.IDiscountView;
import com.zhy.mobilecart.biz.shoppingcart.domain.RefreshEvent;
import com.zhy.mobilecart.manager.AppManager;
import com.zhy.mobilecart.utils.RxBus;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/11/3.
 */

public class AddDiscountActivity extends BaseActivity implements IDiscountView {


    @BindView(R.id.tv_type)
    TextView typeTextView;

    @BindView(R.id.tv_discount)
    TextView discountTextView;

    @BindView(R.id.tv_date)
    TextView dateTextView;


    @BindView(R.id.type_spinner)
    Spinner typeSpinner;

    @BindView(R.id.discount_spinner)
    Spinner discountSpinner;

    private Calendar calendar = Calendar.getInstance();

    private DiscountPresenter discountPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_discount);
        setViewTitle(R.string.add_discount);

        typeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Type.getNames()));
        discountSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));

        discountPresenter = new DiscountPresenter(this);
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
        if (!TextUtils.isEmpty(dateTextView.getText().toString())) {
            Discount discount = new Discount();
            discount.setType(typeSpinner.getSelectedItemPosition());
            discount.setDiscount(discountSpinner.getSelectedItemPosition() + 1);
            discount.setExpirationTime(calendar.getTimeInMillis());
            discountPresenter.addDiscount(discount);

            AppManager.getInstance().finishActivity();

            RxBus.getInstance().post(new RefreshEvent(RefreshEvent.refresh_discount));

        }
    }

    @Override
    public void showDiscounts(List<Discount> discounts) {

    }
}
