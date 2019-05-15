package com.ygy.model;

/**
 * @author ygy
 * @date 2019/5/15 17:41
 */
public class myorder {
    private String name;
    private String price;
    private String detail;
    private String remarks;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getRemarks() {
        return remarks;
    }
}
