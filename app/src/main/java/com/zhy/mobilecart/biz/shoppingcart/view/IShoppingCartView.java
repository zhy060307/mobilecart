package com.zhy.mobilecart.biz.shoppingcart.view;

import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;
import com.zhy.mobilecart.biz.shoppingcart.model.GoodsCalculator;

import java.util.List;

/**
 * <p/>
 * 创建时间: 16/10/30 下午9:39 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public interface IShoppingCartView {
    void showChooseGoodses(List<ChooseGoods> chooseGoodses);

    void showResult(GoodsCalculator.CalculateResult result);
}
