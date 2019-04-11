package com.ygy.dao;

import com.ygy.model.Admin;

/**
 * @author ygy
 * @date 2019/4/11 11:18
 */
public interface adminDao {
    /**
    * @Description:
    * @Param: [Admin]
    * @return: boolean
    * @Author: ygy
    * @Date: 2019/4/11
    */
    boolean addAdmin(Admin Admin);
    /** 
    * @Description:  
    * @Param: [id] 
    * @return: com.ygy.model.Admin 
    * @Author: ygy 
    * @Date: 2019/4/11 
    */ 
    Admin selectById(String id);
    /** 
    * @Description:  
    * @Param: [id] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/11 
    */ 
    boolean deleteByid(String id);
    /** 
    * @Description:  
    * @Param: [admin] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/11 
    */ 
    boolean update(Admin admin);
}
