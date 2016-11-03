package com.zhy.mobilecart.biz.shoppingcart.presenter;

import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;
import com.zhy.mobilecart.biz.shoppingcart.model.GoodsCalculator;
import com.zhy.mobilecart.biz.shoppingcart.model.ShoppingCartModel;
import com.zhy.mobilecart.biz.shoppingcart.view.IShoppingCartView;

import java.util.List;

import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * <p/>
 * 创建时间: 16/10/31 上午1:09 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class ShoppingCartPresenter {

    private IShoppingCartView shoppingCartView;

    private ShoppingCartModel shoppingCartModel;


    public ShoppingCartPresenter(IShoppingCartView shoppingCartView) {
        this.shoppingCartView = shoppingCartView;
        this.shoppingCartModel = new ShoppingCartModel();
    }


    public void queryChooseGoods() {
        this.shoppingCartModel.queryChooseGoods().subscribe(new Action1<RealmResults<ChooseGoods>>() {
            @Override
            public void call(RealmResults<ChooseGoods> chooseGoodses) {
                shoppingCartView.showChooseGoodses(chooseGoodses);

            }
        });
    }

    public void calculate() {

        List<ChooseGoods> chooseGoodses = this.shoppingCartModel.queryChooseGoodees();
        List<Discount> discounts = this.shoppingCartModel.queryDiscounts();
        List<Privileges> privileges = this.shoppingCartModel.queryPrivileges();
        GoodsCalculator calculator = new GoodsCalculator(chooseGoodses, discounts, privileges);
        GoodsCalculator.CalculateResult result = calculator.calculate();
        shoppingCartView.showResult(result);
    }

    public void saveGoods(int goodsItem, int num) {
        this.shoppingCartModel.saveGoods(goodsItem, num);
    }
}
