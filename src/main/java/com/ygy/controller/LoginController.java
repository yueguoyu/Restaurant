package com.ygy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygy.common.HttpClientUtil;
import com.ygy.common.IMoocJSONResult;
import com.ygy.common.JsonUtils;
import com.ygy.common.RedisOperator;
import com.ygy.dao.*;
import com.ygy.mapper.TOrderMapper;
import com.ygy.model.*;



import net.minidev.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.spark.sql.sources.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.ws.rs.FormParam;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class LoginController {
//    @Autowired
//    Testygy test;
   @Autowired
   UserDao userDao;
    @Autowired
    OssclientUtilDao ossclientUtilDao;
    @Autowired
    RestaDao restaDao;
    @Autowired
    BuyerCart buyerCart;
    @Autowired
    TOrderMapper tOrderMapper;
    @Autowired
    private RedisOperator redis;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SvdDao svdDao;

    static ValueOperations valueOperations;
   private WXSessionModel model;
    private Remarks remarks;
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
        remarks=  com.alibaba.fastjson.JSONObject.parseObject(jsonParam.toJSONString(),Remarks.class);
//获取备注
        System.out.println("remarks:"+remarks.getRemarks());

        return "";
    }
    @PostMapping("/submission")
    @ResponseBody
    public TOrder test(@RequestBody JSONObject jsonParam, Model model1) throws IOException {
//获取订单信息
        System.out.println("openid:"+model.getOpenid());
        System.out.println(jsonParam.toJSONString().substring(12,jsonParam.toJSONString().length()-1));

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       String jsonstr= jsonParam.toJSONString().substring(24,jsonParam.toJSONString().length()-2);
       List<Myorder> myorders=JsonUtils.myjsonList(jsonstr);
        String filename= RandomStringUtils.randomAlphanumeric(10);
       for (Myorder myorder:myorders){
            TOrder t=new TOrder();
            t.setName(myorder.getName());
            t.setDetail(myorder.getDetail());
            t.setSum(myorder.getSum());
            t.setNumber(myorder.getNumber());
            t.setPrice(myorder.getPrice());
           //可以方便地修改日期格式
           t.setTime(dateFormat.format( now ));
            t.setOrdernumber(filename);
            t.setRemarks(remarks.getRemarks());
           tOrderMapper.insert(t);
       }
//      将新用户加到menu表中
       for (Myorder myorder:myorders){
           if (!svdDao.hasekey(model.getOpenid(),myorder.getName())){
           List<String> list=svdDao.get(myorder.getName());
            if (list==null){
                List<String> newlist=new ArrayList<>();
                newlist.add(model.getOpenid());
                svdDao.add(myorder.getName(),newlist);
            }else {
                list.add(model.getOpenid());
                svdDao.add(myorder.getName(),list);
            }

           }else {
               continue;
           }
// 用户点餐后openid 表对应的值+1
           svdDao.addMealNumber(model.getOpenid(),myorder.getName());
       }


//        com.alibaba.fastjson.JSONObject Jbject= com.alibaba.fastjson.JSONObject.parseObject(jsonParam.toJSONString());

//        放入redis进行svd计算   redis存储+进行svd计算+打印输出订单
//       svdDao.get("");
        TOrder re=new TOrder();
       re.setTime(dateFormat.format( now ));
       re.setOrdernumber(filename);
        return re;
    }


    @GetMapping("/home")
    public String login(Model model){
        Restaurant restaurant=new Restaurant();
        User user=new User();
        model.addAttribute(user);
        model.addAttribute(restaurant);
        return "index";
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
                model.addAttribute("RestaId", "restaurant");
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
    @Scheduled(cron ="0 0 3 * * ?")
    public void recommend(){

       HashMap<String,Integer> map= (HashMap<String, Integer>) svdDao.getopenid(model.getOpenid());
        List<Map.Entry<String, Integer>> list=svdDao.sort(map);
       Map.Entry<String, Integer> ment=list.get(0);
       String menukey=ment.getKey();
        List<Map.Entry<String, Integer>> menulist=svdDao.sort((HashMap<String, Integer>)svdDao.getmenuid(menukey));

        for (Map.Entry<String, Integer> mapping : menulist){
            System.out.println(mapping.getKey()+": "+mapping.getValue());
            redisTemplate.opsForZSet().add("recommend",mapping.getKey(),mapping.getValue());
        }
    }

}
