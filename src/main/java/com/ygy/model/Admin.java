package com.ygy.model;

public class Admin {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.admin_id
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    private String adminId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.pass
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    private String pass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column admin.rid
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    private String rid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.admin_id
     *
     * @return the value of admin.admin_id
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.admin_id
     *
     * @param adminId the value for admin.admin_id
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.pass
     *
     * @return the value of admin.pass
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    public String getPass() {
        return pass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.pass
     *
     * @param pass the value for admin.pass
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column admin.rid
     *
     * @return the value of admin.rid
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    public String getRid() {
        return rid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column admin.rid
     *
     * @param rid the value for admin.rid
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}