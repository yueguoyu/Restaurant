package com.ygy.controller;

import com.ygy.dao.Testygy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @Autowired
    Testygy test;
    @RequestMapping("/test")
    @ResponseBody
    public String test1(){

        System.out.println("的撒撒旦的撒");
        return "粗 的撒";
    }
    @RequestMapping("/tt")
    public void test2(){
        test.add();
    }
    @RequestMapping("/seclect")
    public String test3(){
       return test.get();
    }
}
