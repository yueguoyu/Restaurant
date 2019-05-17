package com.ygy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author ygy
 * @date 2019/5/17 10:03
 * 推荐
 */
public interface SvdDao {

    /**
    * @Description:  计算相似度
    * @Param: []
    * @return: int
    * @Author: ygy
    * @Date: 2019/5/17
    */
    double Calculation(String name1,String name2);
    /**
    * @Description:  将用户添加到menu的hash表中
    * @Param: [mname, list]
    * @return: void
    * @Author: ygy
    * @Date: 2019/5/17
    */
    void add(String mname,List<String> list);
    /** 
    * @Description: 创建用户hash表，存储用户点单次数
    * @Param: [name] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/5/17 
    */ 
    boolean addOpidTable(String name);


}
