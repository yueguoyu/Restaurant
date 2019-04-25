package com.ygy.controller;

import com.ygy.dao.CacheDao;
import com.ygy.dao.MenuDao;
import com.ygy.mapper.MenuMapper;
import com.ygy.model.Menu;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping("/selectByrid")
    @ResponseBody
    public List<Menu> selectByrid(String rid){
        JSONObject json=new JSONObject();
      List<Menu> list=menuDao.selectByrid("002");
      json.put("list",list);
        return list;
    }
}
