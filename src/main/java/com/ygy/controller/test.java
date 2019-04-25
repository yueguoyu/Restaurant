package com.ygy.controller;

import com.ygy.dao.Testygy;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
    @Autowired
    Testygy test;
    @RequestMapping("/test")
    @ResponseBody
    public String test1(){

        System.out.println("的撒撒旦的撒");
        return "粗 的撒";
    }

    @RequestMapping("/ygy")
    @ResponseBody
    public String ttt(){
        JSONObject json=new JSONObject();
        json.put("name","ygy");

        json.put("numb",2);
        return json.toJSONString();
    }
}
