package com.ygy.mapper;

import com.ygy.model.Menu;
import org.apache.ibatis.annotations.Param;


public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Mar 30 22:25:58 CST 2019
     */
    int deleteByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Mar 30 22:25:58 CST 2019
     */
    int insert(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Mar 30 22:25:58 CST 2019
     */
    int insertSelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Mar 30 22:25:58 CST 2019
     */
    Menu selectByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Mar 30 22:25:58 CST 2019
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu
     *
     * @mbggenerated Sat Mar 30 22:25:58 CST 2019
     */
    int updateByPrimaryKey(Menu record);

    Menu selectByidandmid(@Param("m_rid") String rid, @Param("menu_id") String mid);
}