package com.zhy.mobilecart.biz.mall.view;

import com.zhy.mobilecart.biz.mall.domin.GoodsItem;

import java.util.List;

/**
 * <p/>
 * 创建时间: 16/10/30 下午7:49 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public interface IGoodsView {
    void showGoods(List<GoodsItem> itemList);
}
