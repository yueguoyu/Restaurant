package com.ygy.dao;

import com.ygy.model.Restaurant;
    /**
    * @Author: ygy
    * @Date: 2019/4/2
    */
public interface RestaDao {
    /** 
    * @Description: 餐厅的注册
    * @Param: [restaurant] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/2 
    */ 
    boolean addResta(Restaurant restaurant);
    /**
    * @Description:  删除
    * @Param: [rid]
    * @return: boolean
    * @Author: ygy
    * @Date: 2019/4/2
    */
    boolean deleRestaByid(String rid);
    /** 
    * @Description: 通过id进行查询 
    * @Param: [rid] 
    * @return: com.ygy.model.Restaurant 
    * @Author: ygy 
    * @Date: 2019/4/2 
    */ 
    Restaurant selectByid(String rid);
    /** 
    * @Description: 更改
    * @Param: [restaurant] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/2 
    */ 
    boolean updateRest(Restaurant restaurant);
}
