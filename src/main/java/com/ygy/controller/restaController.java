package com.ygy.controller;

import com.ygy.dao.restaDao;
import com.ygy.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;

/**
 * @author ygy
 * @date 2019/4/2 11:07
 */
@RestController
public class restaController {
    @Autowired
    restaDao restaDao;
    /** 
    * @Description: select by key
    * @Param: [rid] 
    * @return: com.ygy.model.Restaurant 
    * @Author: ygy 
    * @Date: 2019/4/2 
    */ 
    @RequestMapping("/restas")
    public Restaurant selectRestByKey(String rid) {
        Restaurant restaurant = restaDao.selectByid("1");
        return restaurant;
    }
    /**
    * @Description: insert into
    * @Param: [restaurant]
    * @return: void
    * @Author: ygy
    * @Date: 2019/4/2
    */
    @RequestMapping("/restainsert")
    public void  insertRest(Restaurant restaurant1){
        Restaurant restaurant=new Restaurant();
        restaurant.setrId("2");
        restaurant.setName("ygy");
        restaurant.setAddress("和平银座");
        restaurant.setLon(1.0);
        restaurant.setLat(1.1);
        restaurant.setrPhone("12345678901");
        restaurant.setMenuId("123");
        restaDao.addResta(restaurant);
    }
    /**
    * @Description: update
    * @Param: [restaurant]
    * @return: void
    * @Author: ygy
    * @Date: 2019/4/2
    */
    @RequestMapping("/restaupda")
    public void updateRest(Restaurant restaurant){
        restaDao.update(restaurant);
    }
}
