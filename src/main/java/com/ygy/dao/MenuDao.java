package com.ygy.dao;

import com.ygy.model.Menu;

import java.util.List;

/**
 * @author ygy
 * @date 2019/4/3 11:09
 */
public interface MenuDao {
    /**
     * addmenu
    * @Description: addmenu
    * @Param:  menu
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/3 
    */ 
    String addMenu(Menu menu);
    /**
     * 删除菜单订单
    * @Description:  delete by id
    * @Param:  mid
    * @return: java.lang.String
    * @Author: ygy
    * @Date: 2019/4/3
    */
    String deleMenuById(String mid);
    /**
    * @Description:  更改订单
    * @Param: [Menu]
    * @return: boolean
    * @Author: ygy
    * @Date: 2019/4/3
    */
    boolean updateMenu(Menu Menu);
    /**
    * @Description: 查询订单
    * @Param: [mid]
    * @return: com.ygy.model.Menu
    * @Author: ygy
    * @Date: 2019/4/3
    */
    Menu selectMenuByid(String mid);
    /** 
    * @Description: 通过rid和mid获取菜单详细信息
    * @Param: [rid, mid] 
    * @return: com.ygy.model.Menu 
    * @Author: ygy 
    * @Date: 2019/4/3 
    */ 
    Menu selectByidandmid(String rid,String mid);

    List<Menu> selectByrid(String rid);

    void deleMenuByname(String name);

    Menu SelectByName(String mname);



}
