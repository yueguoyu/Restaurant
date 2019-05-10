package com.ygy.model;

import java.io.Serializable;

/**
 * @author ygy
 * @date 2019/5/10 10:56
 * 购物车数据
 */
public class LineItem implements Serializable {
    /**
     * 商品名称,id
     */
    private String name;
    /**
     * 商品数量
     */
    private int quantity;
    /**
     * 商品的价格
     */
    private long price;

    public int getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
