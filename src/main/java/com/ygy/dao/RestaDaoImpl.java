package com.ygy.dao;


import com.ygy.mapper.RestaurantMapper;
import com.ygy.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @program: restaurant
 * @description:
 * @author: ygy
 * @create: 2019-04-02 10:49
 **/
@SuppressWarnings("ALL")
@Service
public class RestaDaoImpl implements RestaDao {
    @Autowired
    RestaurantMapper restaurantMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     *注册
     * 事务场景中，抛出异常被catch后，如果需要回滚，一定要手动回滚事务。
     * @param restaurant
     * @return bollean
      *
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addResta(Restaurant restaurant) {
        boolean rest=false;

                restaurantMapper.insert(restaurant);
        return rest;
    }
    /** 
    * @Description: delete by id
    * @Param: [rid] 
    * @return: boolean 
    * @Author: ygy 
    * @Date: 2019/4/2 
    */ 
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleRestaByid(String rid) {
        boolean rest=false;
        try {
            restaurantMapper.deleteByPrimaryKey(rid);
            rest=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("deleRestaByid出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rest=false;
        }
        return rest;
    }
    /**
    * @Description:  update 
    * @Param: [restaurant]
    * @return: boolean
    * @Author: ygy
    * @Date: 2019/4/2
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRest(Restaurant restaurant) {
        boolean rest=false;
        try {
            restaurantMapper.updateByPrimaryKey(restaurant);
            rest=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("updateRest出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            rest=false;
        }
        return rest;
    }
    /** 
    * @Description:  select  by id
    * @Param: [rid] 
    * @return: com.ygy.model.Restaurant 
    * @Author: ygy 
    * @Date: 2019/4/2 
    */ 
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Restaurant selectByid(String rid) {
        Restaurant restaurant=null;
        try {
            restaurant=restaurantMapper.selectByPrimaryKey( rid);
        }catch (Exception e){
           e.printStackTrace();
            logger.error("selectByid出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return restaurant;
    }
}

