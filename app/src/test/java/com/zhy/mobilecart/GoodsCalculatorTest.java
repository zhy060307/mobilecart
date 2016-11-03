package com.zhy.mobilecart;

import com.zhy.mobilecart.biz.me.domain.Discount;
import com.zhy.mobilecart.biz.me.domain.Privileges;
import com.zhy.mobilecart.biz.me.domain.Type;
import com.zhy.mobilecart.biz.shoppingcart.domain.ChooseGoods;
import com.zhy.mobilecart.biz.shoppingcart.model.GoodsCalculator;
import com.zhy.mobilecart.utils.DateUtils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GoodsCalculatorTest {


    private GoodsCalculator calculator;

    private List<ChooseGoods> goodsList;

    private List<Discount> discountList;

    private List<Privileges> privilegesList;

    @Before
    public void before() {

        goodsList = new ArrayList<>();
        discountList = new ArrayList<>();
        privilegesList = new ArrayList<>();
        calculator = new GoodsCalculator(goodsList, discountList, privilegesList);

    }

    @Test
    public void calculate_test1() {

        goodsList = new ArrayList<>();
        discountList = new ArrayList<>();
        privilegesList = new ArrayList<>();
        calculator = new GoodsCalculator(goodsList, discountList, privilegesList);


        goodsList.add(new ChooseGoods(1, 0, Type.ELECTRONIC.getIndex(), 2399.00, "ipad"));
        goodsList.add(new ChooseGoods(2, 1, Type.FOOD.getIndex(), 3.00, "面包"));
        goodsList.add(new ChooseGoods(5, 2, Type.BEER.getIndex(), 2.00, "啤酒"));
        goodsList.add(new ChooseGoods(1, 3, Type.FOOD.getIndex(), 20.00, "蛋糕"));

        discountList.add(new Discount(0, Type.ELECTRONIC.getIndex(), 7.00, new Date().getTime()));
        privilegesList.add(new Privileges(0, 1000.00, 200.00, DateUtils.getStringToDate("2020.03.01").getTime()));

        GoodsCalculator.CalculateResult result = calculator.calculate();


        Assert.assertEquals(1715.3, result.getTotal());
    }


    @Test
    public void calculate_test2() {

        goodsList = new ArrayList<>();
        discountList = new ArrayList<>();
        privilegesList = new ArrayList<>();
        calculator = new GoodsCalculator(goodsList, discountList, privilegesList);


        goodsList.add(new ChooseGoods(1, 0, Type.ELECTRONIC.getIndex(), 2399.00, "ipad"));
        goodsList.add(new ChooseGoods(2, 1, Type.FOOD.getIndex(), 3.00, "面包"));
        goodsList.add(new ChooseGoods(5, 2, Type.BEER.getIndex(), 2.00, "啤酒"));
        goodsList.add(new ChooseGoods(1, 3, Type.FOOD.getIndex(), 20.00, "蛋糕"));

        privilegesList.add(new Privileges(0, 1000.00, 200.00, DateUtils.getStringToDate("2020.03.01").getTime()));

        GoodsCalculator.CalculateResult result = calculator.calculate();


        Assert.assertEquals(2435.0, result.getTotal());
    }
}