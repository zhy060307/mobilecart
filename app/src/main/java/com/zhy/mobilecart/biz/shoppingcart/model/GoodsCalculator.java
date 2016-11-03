package com.zhy.mobilecart.biz.shoppingcart.model;

import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;
import com.zhy.mobilecart.utils.DateUtils;
import com.zhy.mobilecart.utils.ListUtils;

import java.util.Date;
import java.util.List;

/**
 * 计算总价
 * <p/>
 * 创建时间: 16/10/31 上午2:17 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class GoodsCalculator {

    private List<ChooseGoods> chooseGoodses;
    private List<Discount> discounts;
    private List<Privileges> privileges;


    private double discountMoney;

    private double prividegeMoney;

    public GoodsCalculator(List<ChooseGoods> chooseGoodses,
                           List<Discount> discounts,
                           List<Privileges> privileges) {
        this.chooseGoodses = chooseGoodses;
        this.discounts = discounts;
        this.privileges = privileges;
    }

    public CalculateResult calculate() {

        //先打折再优惠券

        CalculateResult result = new CalculateResult();


        if (ListUtils.isNotEmpty(chooseGoodses)) {

            result.setTotal(getTotalPrice());
            if (ListUtils.isNotEmpty(discounts)) {
                Discount discount = getBestDiscount();
                if (null != discount) {
                    result.setDiscount(discount);
                    result.setTotal(result.getTotal() - discountMoney);

                } else {
                    if (ListUtils.isNotEmpty(privileges)) {
                        Privileges privileges = getBestPrivileges();
                        if (null != privileges) {
                            result.setPrivileges(privileges);
                            result.setTotal(result.getTotal() - privileges.getCreditAmount());
                        }
                    }
                }
            }
        }

        return result;

    }

    private Discount getBestDiscount() {

        double discountMoney = 0;
        Discount discountTemp = null;
        for (Discount discount : discounts) {
            if (DateUtils.isBigThan(new Date(discount.getExpirationTime()), new Date())) {

                int type = discount.getType();

                ChooseGoods discountGoods = getDiscountGoods(type);
                if (discountGoods != null) {
                    double total = discountGoods.getGoodsPrice() * discountGoods.getCount();

                    double disMoney = total * (10 - discount.getDiscount()) * 0.1;
                    if (disMoney > discountMoney) {
                        discountMoney = disMoney;
                        discountTemp = discount;
                    }

                }
            }
        }

        this.discountMoney = discountMoney;
        return discountTemp;
    }

    private ChooseGoods getDiscountGoods(int type) {
        for (ChooseGoods chooseGoodse : chooseGoodses) {
            if (chooseGoodse.getGoodsType() == type) {
                return chooseGoodse;
            }
        }

        return null;
    }

    private Privileges getBestPrivileges() {

        double total = getTotalPrice();
        Privileges temp = null;
        for (Privileges privilege : privileges) {
            if (DateUtils.isBigThan(new Date(privilege.getExpirationTime()), new Date())) {

                if (total >= privilege.getConsumeAmount()) {
                    if (temp == null) {
                        temp = privilege;
                    } else {
                        if (temp.getCreditAmount() < privilege.getCreditAmount()) {
                            temp = privilege;
                        }
                    }
                }
            }
        }

        return temp;
    }

    private double getTotalPrice() {
        double total = 0;
        for (ChooseGoods chooseGoodse : chooseGoodses) {
            total += (chooseGoodse.getCount() * chooseGoodse.getGoodsPrice());
        }

        return total;
    }


    public static class CalculateResult {
        private double total;
        private Discount discount;
        private Privileges privileges;

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public Discount getDiscount() {
            return discount;
        }

        public void setDiscount(Discount discount) {
            this.discount = discount;
        }

        public Privileges getPrivileges() {
            return privileges;
        }

        public void setPrivileges(Privileges privileges) {
            this.privileges = privileges;
        }
    }
}
