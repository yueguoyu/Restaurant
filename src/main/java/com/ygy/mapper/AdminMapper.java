package com.ygy.mapper;

import com.ygy.model.Admin;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    int deleteByPrimaryKey(String adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    Admin selectByPrimaryKey(String adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Thu Apr 11 11:11:22 CST 2019
     */
    int updateByPrimaryKey(Admin record);
}