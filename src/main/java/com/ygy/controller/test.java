package com.ygy.controller;

import com.ygy.dao.SvdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ygy
 * @date 2019/5/17 13:57
 */
@Controller
public class test {
    @Autowired
    SvdDao svdDao;
    @RequestMapping("/test")
    public void tes(){

    }
}
