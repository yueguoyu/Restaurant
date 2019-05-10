package com.ygy.controller;

import com.ygy.dao.*;
import com.ygy.mapper.UserMapper;
import com.ygy.model.LineItem;
import com.ygy.model.Menu;
import com.ygy.model.Restaurant;
import com.ygy.model.User;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    Testygy test;
   @Autowired
   UserDao userDao;
    @Autowired
    OssclientUtilDao ossclientUtilDao;
    @Autowired
    RestaDao restaDao;
    @Autowired
    BuyerCart buyerCart;
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
// 直接将json信息打印出来
        LineItem lineItem=new LineItem();
        lineItem.setName("ygy");
        lineItem.setPrice(10);
        lineItem.setQuantity(2);
        buyerCart.add("dsa",lineItem);
        List<LineItem> list= buyerCart.getall("dsa");
        for (LineItem i:list){
            System.out.println(i.getName());
        }
        System.out.println(buyerCart.getCartTotal("dsa"));
        return "0123";
    }
    @GetMapping("/home")
    public String login(Model model){
        Restaurant restaurant=new Restaurant();
        User user=new User();
        model.addAttribute(user);
        model.addAttribute(restaurant);
        return "login";
    }

    @PostMapping("/signIn")
    public  String signIn(@ModelAttribute Restaurant restaurant,Model model){
          String pass=restaDao.selectByid(restaurant.getrId()).getPass();
          Menu menu=new Menu();
          model.addAttribute(menu);
          if (pass.equals(restaurant.getPass())){
              model.addAttribute("RestaId",restaurant.getrId());
                return "upload";
          }else {
              model.addAttribute("erro","用户名或密码错误");
              return "login";
          }
    }

    @PostMapping("/register")
    public  String  register(@ModelAttribute Restaurant restaurant){
        if (restaurant!=null){
            restaDao.addResta(restaurant);
        }
            return "login";
    }


    @GetMapping("/hello")
    public String hello(Model model){
        Menu menu=new Menu();
        model.addAttribute(menu);
        return "upload";
    }

}
