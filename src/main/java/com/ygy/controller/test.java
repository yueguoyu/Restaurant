package com.ygy.controller;

import com.ygy.dao.OssclientUtilDao;
import com.ygy.dao.Testygy;
import com.ygy.model.Menu;
import net.minidev.json.JSONObject;
import org.apache.http.entity.ContentType;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class test {
    @Autowired
    Testygy test;
    @Autowired
    OssclientUtilDao ossclientUtilDao;
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
    @GetMapping("/ttt")
    @ResponseBody
    public String test(){
     String url= ossclientUtilDao.fileUplodnew("C:\\Users\\ygy\\Pictures\\webwxgetmsgimg (4).jpg","yyy.jpg","img/ygy/");
        return url;
    }


    @GetMapping("/hello")
    public String hello(Model model){
        Menu menu=new Menu();
        model.addAttribute(menu);
        return "upload";
    }

}
