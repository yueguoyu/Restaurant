package com.ygy.dao;

import com.ygy.mapper.MenuMapper;
import com.ygy.model.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author ygy
 * @date 2019/4/3 11:21
 */
@Service
public class MenuDaoImpl implements MenuDao {
    @Autowired
    MenuMapper menuMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenu(Menu menu) {
        boolean rest=false;
        try {
            menuMapper.updateByPrimaryKey(menu);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("addResta出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rest=true;
        }
        return rest;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu selectMenuByid(String mid) {
        Menu menu=null;
        try {
           menu=menuMapper.selectByPrimaryKey(mid);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectMenuByid出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return menu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addMenu(Menu menu) {
        String rest=null;
        try {
            menuMapper.insert(menu);
            rest=menu.getMenuId();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("addMenu出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleMenuById(String mid) {
        String rest=null;
        try{
            menuMapper.deleteByPrimaryKey(mid);
            rest=mid;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("deleMenuById",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu selectByidandmid(String rid, String mid) {
        Menu menu=null;
        try{
          menu=menuMapper.selectByidandmid(rid,mid);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectByidandmid",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return menu;
    }
}
