package com.ygy.mapper;

import com.ygy.model.TOrder;

import java.util.List;

public interface TOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    int insert(TOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    int insertSelective(TOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    TOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    int updateByPrimaryKeySelective(TOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbggenerated Tue May 21 11:14:13 CST 2019
     */
    int updateByPrimaryKey(TOrder record);
    List<TOrder> selectAll();
    List<TOrder> selectByOpenid(String ordernumber);
    List<TOrder> selectByTime(String time);
    List<TOrder> selectByNumber(String ordernumber);

}