package com.ygy.dao;

import com.ygy.model.User;

/**
 * @author ygy
 * @date 2019/4/11 14:54
 */
public interface userDao{
    /** 
    * @Description:  
    * @Param: [user] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/11 
    */ 
    boolean addUser(User user);
    /** 
    * @Description:  
    * @Param: [uid] 
    * @return: com.ygy.model.User 
    * @Author: ygy 
    * @Date: 2019/4/11 
    */ 
    User selectUserById(String uid);
    /** 
    * @Description:  
    * @Param: [uid] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/11 
    */ 
    boolean deleteUserById(String uid);
    /** 
    * @Description:  
    * @Param: [user] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/11 
    */ 
    boolean updateUser(User user);
}
