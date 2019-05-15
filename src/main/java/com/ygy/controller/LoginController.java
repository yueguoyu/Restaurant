package com.ygy.controller;

import com.ygy.common.HttpClientUtil;
import com.ygy.common.IMoocJSONResult;
import com.ygy.common.RedisOperator;
import com.ygy.dao.*;
import com.ygy.model.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RedisOperator redis;
    @Autowired
    RedisTemplate redisTemplate;

    static ValueOperations valueOperations;
    WXSessionModel model;
    @PostMapping("/wxLogin")
    @ResponseBody
    public IMoocJSONResult wxLogin(String code) {
        valueOperations=redisTemplate.opsForValue();
        System.out.println("wxlogin - code: " + code);

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", "wx39b5cf216a87faa6");
        param.put("secret", "2d6ba33a70348759be5795f335708ea1");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);

         model= com.alibaba.fastjson.JSONObject.parseObject(wxResult,WXSessionModel.class);

//		WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);

        // 存入session到redis
        valueOperations.set("openid:"+model.getOpenid(),model.getSession_key(),1000 * 60 * 30);
//获取openid
        System.out.println(model.getOpenid());
        return IMoocJSONResult.ok();
    }


    @RequestMapping("/ygy")
    @ResponseBody
    public String ttt(@RequestBody JSONObject jsonParam){

        System.out.println(jsonParam.toJSONString());
        Remarks remarks=  com.alibaba.fastjson.JSONObject.parseObject(jsonParam.toJSONString(),Remarks.class);
//获取备注
        System.out.println("remarks:"+remarks.getRemarks());

        return "";
    }
    @PostMapping("/ttt")
    @ResponseBody
    public String test(@RequestBody JSONObject jsonParam){
//获取订单信息
        System.out.println(jsonParam.toJSONString());
        System.out.println(model.getOpenid());
      myorder myorder= com.alibaba.fastjson.JSONObject.parseObject(jsonParam.toJSONString(),myorder.class);


//        放入redis进行svd计算   redis存储+进行svd计算+打印输出订单
        return jsonParam.toJSONString();
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
        try {
            String pass = restaDao.selectByid(restaurant.getrId()).getPass();
            if (pass == null) {
                model.addAttribute("erro", "请注册用户！");
                return "login";
            }
            Menu menu = new Menu();
            model.addAttribute(menu);
            if (pass.equals(restaurant.getPass())) {
                model.addAttribute("RestaId", restaurant.getrId());
                return "upload";
            } else {
                model.addAttribute("erro", "用户名或密码错误");
                return "login";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("erro", "请注册用户！");
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
