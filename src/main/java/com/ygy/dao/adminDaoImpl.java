package com.ygy.dao;

import com.ygy.mapper.AdminMapper;
import com.ygy.model.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author ygy
 * @date 2019/4/11 11:23
 */
public class adminDaoImpl implements adminDao {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RestaDao restaDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Admin selectById(String id) {
        Admin admin=null;
         try{
            admin=adminMapper.selectByPrimaryKey(id);
            admin.setRestaurant(restaDao.selectByid(admin.getRid()));
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectById出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return admin;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        boolean rest=false;
        try{
            adminMapper.insert(admin);
            restaDao.addResta(admin.getRestaurant());
            rest=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("addAdmin出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    public boolean deleteByid(String id) {
        boolean rest=false;
        try{
            restaDao.deleRestaByid(adminMapper.selectByPrimaryKey(id).getRid());
            adminMapper.deleteByPrimaryKey(id);
            rest=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("deleteByid出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Override
    public boolean update(Admin admin) {
        boolean rest=false;
        try{
            adminMapper.updateByPrimaryKey(admin);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("update出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }
}
