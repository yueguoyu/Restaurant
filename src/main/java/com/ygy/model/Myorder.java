package com.ygy.model;

/**
 * @author ygy
 * @date 2019/5/15 17:41
 */
public class Myorder {
    private String name;
    private String price;
    private String detail;
    private String number;
    private String sum;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getNumber() {
        return number;
    }

    public String getSum() {
        return sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getPrice() {
        return price;
    }


}
