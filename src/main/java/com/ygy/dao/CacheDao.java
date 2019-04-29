package com.ygy.dao;

import com.ygy.model.Menu;
import com.ygy.model.Restaurant;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ygy
 * @date 2019/4/3 11:44
 * 将数据放到redis中
 */
public interface CacheDao {
    /**
    * @Description:  当缓存中没有时将menu添加到hash中
    * @Param:   restaurant
    * @return: int
    * @Author: ygy
    * @Date: 2019/4/3
    */
    boolean addMenuCache(String r_id,Menu menu);
    /**
    * @Description:  从缓存中获取
    * @Param: rid
    * @return: com.ygy.model.Restaurant
    * @Author: ygy
    * @Date: 2019/4/3
    */
    Menu selectMenuByCache(String rid,String mid);
    /** 
    * @Description: 获得餐厅全部菜单
    * @Param: [rid] 
    * @return: java.util.Map<java.lang.String,com.ygy.model.Menu> 
    * @Author: ygy 
    * @Date: 2019/4/3 
    */ 
    List<Menu> selectMenuAll(String rid);




}
