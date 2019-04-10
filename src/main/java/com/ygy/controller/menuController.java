package com.ygy.controller;

import com.ygy.dao.CacheDao;
import com.ygy.dao.MenuDao;
import com.ygy.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ygy
 * @date 2019/4/4 14:58
 */
@RestController
public class menuController {
    @Autowired
    CacheDao cacheDao;
    @Autowired
    MenuDao menuDao;
    @RequestMapping("/menuSelect")
    public String menuSelect(String rid,String mid){
        System.out.println(cacheDao.selectMenuByCache("1","1"));

       return "000";
    }
}
