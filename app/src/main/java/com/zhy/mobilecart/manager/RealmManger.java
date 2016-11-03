package com.zhy.mobilecart.manager;

import com.zhy.mobilecart.biz.mall.domin.GoodsItem;
import com.zhy.mobilecart.biz.mall.domin.GoodsType;
import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;
import com.zhy.mobilecart.utils.DateUtils;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * TODO 功能描述
 * <p/>
 * 创建时间: 16/10/30 下午6:55 <br/>
 *
 * @author zhaohaiyang
 * @since v0.0.1
 */

public class RealmManger {

    private Realm mRealm;
    private static RealmManger instance;

    public static RealmManger getInstance() {

        if (null == instance) {
            synchronized (RealmManger.class) {
                if (null == instance) {
                    instance = new RealmManger();
                }
            }
        }

        return instance;

    }

    private RealmManger() {
        mRealm = Realm.getDefaultInstance();
        initDB();
    }

    private void initDB() {
        initGoodsType();
        initGoodsItem();
        initDiscount();
    }

    private void initDiscount() {
        mRealm.beginTransaction();
        mRealm.delete(Discount.class);
        mRealm.delete(Privileges.class);
        mRealm.delete(ChooseGoods.class);

        Discount discount = mRealm.createObject(Discount.class);
        discount.setType(0);
        discount.setId(0);
        discount.setDiscount(7.0);
        discount.setExpirationTime(new Date().getTime());


        Privileges privileges = mRealm.createObject(Privileges.class);
        privileges.setId(0);
        privileges.setConsumeAmount(1000);
        privileges.setCreditAmount(200);
        privileges.setExpirationTime(DateUtils.getStringToDate("2020.03.01").getTime());


        mRealm.commitTransaction();
    }

    private void initGoodsItem() {
        mRealm.beginTransaction();
        mRealm.delete(GoodsItem.class);
        mRealm.commitTransaction();


        mRealm.beginTransaction();
        GoodsItem item0 = mRealm.createObject(GoodsItem.class);
        item0.setId(0);
        item0.setType(0);
        item0.setName("ipad");
        item0.setPrice(2399.00);


        GoodsItem item1 = mRealm.createObject(GoodsItem.class);
        item1.setId(1);
        item1.setType(0);
        item1.setName("iphone");
        item1.setPrice(5288.00);


        GoodsItem item2 = mRealm.createObject(GoodsItem.class);
        item2.setId(2);
        item2.setType(0);
        item2.setName("显示器");
        item2.setPrice(899.00);


        GoodsItem item3 = mRealm.createObject(GoodsItem.class);
        item3.setId(3);
        item3.setType(0);
        item3.setName("笔记本电脑");
        item3.setPrice(4599.00);


        GoodsItem item4 = mRealm.createObject(GoodsItem.class);
        item4.setId(4);
        item4.setType(0);
        item4.setName("键盘");
        item4.setPrice(68.00);


        GoodsItem item5 = mRealm.createObject(GoodsItem.class);
        item5.setId(5);
        item5.setType(1);
        item5.setName("面包");
        item5.setPrice(3.00);

        GoodsItem item6 = mRealm.createObject(GoodsItem.class);
        item6.setId(6);
        item6.setType(1);
        item6.setName("饼干");
        item6.setPrice(5.00);


        GoodsItem item7 = mRealm.createObject(GoodsItem.class);
        item7.setId(7);
        item7.setType(1);
        item7.setName("蛋糕");
        item7.setPrice(20.00);


        GoodsItem item8 = mRealm.createObject(GoodsItem.class);
        item8.setId(8);
        item8.setType(1);
        item8.setName("牛肉");
        item8.setPrice(25.00);


        GoodsItem item9 = mRealm.createObject(GoodsItem.class);
        item9.setId(9);
        item9.setType(1);
        item9.setName("鱼");
        item9.setPrice(13.00);


        GoodsItem item10 = mRealm.createObject(GoodsItem.class);
        item10.setId(10);
        item10.setType(1);
        item10.setName("蔬菜");
        item10.setPrice(3.00);


        GoodsItem item11 = mRealm.createObject(GoodsItem.class);
        item11.setId(11);
        item11.setType(2);
        item11.setName("餐巾纸");
        item11.setPrice(10.00);

        GoodsItem item12 = mRealm.createObject(GoodsItem.class);
        item12.setId(12);
        item12.setType(2);
        item12.setName("收纳箱");
        item12.setPrice(20.00);


        GoodsItem item13 = mRealm.createObject(GoodsItem.class);
        item13.setId(13);
        item13.setType(2);
        item13.setName("收纳箱");
        item13.setPrice(20.00);


        GoodsItem item14 = mRealm.createObject(GoodsItem.class);
        item14.setId(14);
        item14.setType(2);
        item14.setName("咖啡杯");
        item14.setPrice(5.00);

        GoodsItem item15 = mRealm.createObject(GoodsItem.class);
        item15.setId(15);
        item15.setType(2);
        item15.setName("雨伞");
        item15.setPrice(45.00);

        GoodsItem item16 = mRealm.createObject(GoodsItem.class);
        item16.setId(16);
        item16.setType(3);
        item16.setName("啤酒");
        item16.setPrice(2.00);

        GoodsItem item17 = mRealm.createObject(GoodsItem.class);
        item17.setId(17);
        item17.setType(3);
        item17.setName("白酒");
        item17.setPrice(150.00);


        GoodsItem item18 = mRealm.createObject(GoodsItem.class);
        item18.setId(18);
        item18.setType(3);
        item18.setName("伏特加");
        item18.setPrice(230.00);

        mRealm.commitTransaction();
    }


    private void initGoodsType() {
        mRealm.beginTransaction();
        mRealm.delete(GoodsType.class);
        mRealm.commitTransaction();


        mRealm.beginTransaction();
        GoodsType type0 = mRealm.createObject(GoodsType.class);
        type0.setId(0);
        type0.setName("电子");

        GoodsType type1 = mRealm.createObject(GoodsType.class);
        type1.setId(1);
        type1.setName("食品");

        GoodsType type2 = mRealm.createObject(GoodsType.class);
        type2.setId(2);
        type2.setName("日用品");


        GoodsType type3 = mRealm.createObject(GoodsType.class);
        type3.setId(3);
        type3.setName("酒类");

        mRealm.commitTransaction();
    }


    public List<GoodsItem> queryGoodsByType(int goodsType) {
        RealmResults<GoodsItem> results = mRealm.where(GoodsItem.class).equalTo("type", goodsType).findAll();
        return mRealm.copyFromRealm(results);
    }
}
