package com.zhy.mobilecart.biz.shoppingcart.view.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.mobilecart.R;
import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.me.domain.Type;
import com.zhy.mobilecart.biz.shoppingcart.model.GoodsCalculator;
import com.zhy.mobilecart.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p/>
 * 创建时间: 16/10/31 上午2:03 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class TotalConsumeView extends RelativeLayout {

    private View rootView;


    @BindView(R.id.ll_discount_view)
    View discountView;

    @BindView(R.id.ll_privilege_view)
    View privilegeView;


    @BindView(R.id.tv_discount_description)
    TextView tvDisCountDes;


    @BindView(R.id.tv_discount_expirationTime)
    TextView tvDiscountExp;

    @BindView(R.id.tv_privilege_expirationTime)
    TextView tvPrivilegeExp;

    @BindView(R.id.tv_privilege_description)
    TextView tvprivilegeDes;


    @BindView(R.id.tv_total)
    TextView tvTotal;


    public TotalConsumeView(Context context) {
        this(context, null);
    }

    public TotalConsumeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TotalConsumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.list_item_total_price, this, true);
        rootView.setBackgroundColor(context.getResources().getColor(R.color.white));

        ButterKnife.bind(this, rootView);
    }


    public void showView(GoodsCalculator.CalculateResult result) {

        if (null != result) {
            Discount discount = result.getDiscount();
            discountView.setVisibility(discount == null ? GONE : VISIBLE);

            if (null != discount) {
                tvDisCountDes.setText(Type.typeOf(discount.getType()).getName() + "类 " + discount.getDiscount() + "折");
                tvDiscountExp.setText("截止日期:" + DateUtils.getDateToString(new Date(discount.getExpirationTime())));
            }

            Privileges privileges = result.getPrivileges();

            privilegeView.setVisibility(privileges == null ? GONE : VISIBLE);
            if (null != privileges) {
                tvprivilegeDes.setText(String.format("满%s减%s优惠券", privileges.getConsumeAmount(), privileges.getCreditAmount()));
                tvPrivilegeExp.setText("截止日期:" + DateUtils.getDateToString(new Date(privileges.getExpirationTime())));
            }

            tvTotal.setText("合计: ￥" + result.getTotal());
        }

    }
}
