package com.ygy.dao;

import com.ygy.mapper.UserMapper;
import com.ygy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author ygy
 * @date 2019/4/11 14:58
 */
@Service
public class UserDaoImpl implements UserDao {
    @Autowired
    UserMapper userMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean addUser(User user) {
        boolean rest=false;
        try{
            userMapper.insert(user);
            rest=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("addUser出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    public boolean deleteUserById(String uid) {
        boolean rest=false;
        try{
            userMapper.deleteByPrimaryKey(uid);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("deleteUserById出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    public boolean updateUser(User user) {
        boolean rest=false;
        try {
            userMapper.updateByPrimaryKey(user);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("updateUser出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    public User selectUserById(String uid) {
        User user=null;
        try{
            userMapper.selectByPrimaryKey(uid);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectUserById出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return user;
    }
}
