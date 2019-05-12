package com.ygy.mapper;

import com.ygy.model.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sun May 12 11:29:17 CST 2019
     */
    int deleteByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sun May 12 11:29:17 CST 2019
     */
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sun May 12 11:29:17 CST 2019
     */
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sun May 12 11:29:17 CST 2019
     */
    Menu selectByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sun May 12 11:29:17 CST 2019
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sun May 12 11:29:17 CST 2019
     */
    int updateByPrimaryKey(Menu record);

    Menu selectByidandmid(String rid,String mid);
    List<Menu> selectByrid(String rid);
}