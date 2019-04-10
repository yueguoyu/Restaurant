package com.ygy.dao;

import com.ygy.model.TOrder;

/**
 * @author ygy
 * @date 2019/4/9 11:22
 */
public interface OrderDao {
    /**
    * @Description: 添加菜
    * @Param: [order]
    * @return: boolean
    * @Author: ygy
    * @Date: 2019/4/9
    */
    boolean addOrder(TOrder order);
    /**
    * @Description: 根据桌号查询菜
    * @Param: [tableid]
    * @return: com.ygy.model.Order
    * @Author: ygy
    * @Date: 2019/4/9
    */
    TOrder selectOrderByTableId(int tableid);
    /**
    * @Description:  删除桌上的菜
    * @Param: [tableid]
    * @return: boolean
    * @Author: ygy
    * @Date: 2019/4/9
    */
    boolean deleteOrderByTableID(int tableid);

}
