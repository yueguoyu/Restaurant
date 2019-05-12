package com.ygy.controller;


import com.ygy.dao.BuyerCart;
import com.ygy.dao.OrderDao;
import com.ygy.model.LineItem;
import com.ygy.model.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ygy
 * @date 2019/4/9 15:01
 */
@RestController
public class orderControlle {
    @Autowired
    OrderDao orderDao;
    @Autowired
    BuyerCart buyerCart;
    @PostMapping("/selectOrder")
    @ResponseBody
    public TOrder insertOrder(int tableid,@ModelAttribute LineItem lineItem,@PathVariable("username") String userid){
        buyerCart.add(userid,lineItem);
        return orderDao.selectOrderByTableId(tableid);
    }
    @GetMapping("/deleteOrder")
    public void deleteOrder(int tableid){
        orderDao.deleteOrderByTableID(tableid);
    }
    @PostMapping("/addOrder")
    public void add(TOrder tOrder){
        orderDao.addOrder(tOrder);
    }

}
