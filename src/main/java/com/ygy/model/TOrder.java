package com.ygy.model;

public class TOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.id
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.name
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.sum
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    private String sum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.price
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    private String price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.number
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    private String number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.detail
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    private String detail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.time
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    private String time;
    private  String ordernumber;

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getOrdernumber() {
        return ordernumber;
    }
    private  String remarks;

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.id
     *
     * @return the value of t_order.id
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.id
     *
     * @param id the value for t_order.id
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.name
     *
     * @return the value of t_order.name
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.name
     *
     * @param name the value for t_order.name
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.sum
     *
     * @return the value of t_order.sum
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public String getSum() {
        return sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.sum
     *
     * @param sum the value for t_order.sum
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public void setSum(String sum) {
        this.sum = sum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.price
     *
     * @return the value of t_order.price
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.price
     *
     * @param price the value for t_order.price
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.number
     *
     * @return the value of t_order.number
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.number
     *
     * @param number the value for t_order.number
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.detail
     *
     * @return the value of t_order.detail
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.detail
     *
     * @param detail the value for t_order.detail
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.time
     *
     * @return the value of t_order.time
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public String getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.time
     *
     * @param time the value for t_order.time
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    public void setTime(String time) {
        this.time = time;
    }
}