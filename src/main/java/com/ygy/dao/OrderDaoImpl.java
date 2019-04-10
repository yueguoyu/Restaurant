package com.ygy.dao;

import com.ygy.mapper.TOrderMapper;
import com.ygy.model.TOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author ygy
 * @date 2019/4/9 11:31
 */
@Service
public class OrderDaoImpl implements  OrderDao {
    @Autowired
    TOrderMapper orderMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean addOrder(TOrder order) {
        boolean rest=false;
        try{
            orderMapper.insert(order);
            rest=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("addOrder出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    public boolean deleteOrderByTableID(int tableid) {
        boolean rest=false;
        try{
//            orderMapper.
            rest=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("addOrder出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return rest;
    }

    @Override
    public TOrder selectOrderByTableId(int tableid) {
        TOrder order= null;
        try{
            order=orderMapper.selectByTableID(tableid);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("addOrder出错",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return order;
    }
}
