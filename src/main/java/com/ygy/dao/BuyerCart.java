package com.ygy.dao;

import com.ygy.model.LineItem;

import java.util.List;


/**
 * 购物车接口
 */
public interface BuyerCart {
    /**
     * 购物车添加商品
     * @param username
     * @param
     */
    void add(String username, LineItem lineItem);

    /**
     * 返回购物车中的所有商品
     * @param username
     * @return
     */
    List<LineItem> getall(String username);

    /**
     * 得到购物车中所有商品的总价值
     * @param username
     * @return
     */
    long getCartTotal(String username);

    /**
     * 删除购物车中商品
     * @param username
     * @param
     */
    void dele(String username,LineItem lineItem);
}