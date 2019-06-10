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
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.redis.core.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.print.DocFlavor;
import javax.ws.rs.FormParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
@Autowired
MenuDao menuDao;
    static ValueOperations valueOperations;
    private HashOperations<String,String,Restaurant> hashOperations;
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
//        创建openid redis表

      String remname=  menuDao.selectByrid("restaurant").get(0).getmName();
      if (!svdDao.hasekey(model.getOpenid(),remname)){
          svdDao.addOpidTable(model.getOpenid());
      }
    HashMap<String,Integer> map=(HashMap<String, Integer>) svdDao.getopenid(model.getOpenid());
      ZSetOperations<String,String> zsetOperations=redisTemplate.opsForZSet();

        List<Map.Entry<String, Integer>> listsvd=svdDao.sort(map);

          HashMap<String,Integer> map2=(HashMap<String, Integer>)svdDao.getmenuid(listsvd.get(0).getKey());
            List<Map.Entry<String, Integer>> entries=svdDao.sort(map2);
            for (Map.Entry<String, Integer> integerEntry:entries){
                    zsetOperations.add("recommendMenu",integerEntry.getKey(),integerEntry.getValue());
            }
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
            if (remarks.getRemarks()!=null){
                t.setRemarks(remarks.getRemarks());
            }else {
                t.setRemarks("null");
            }
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
            Restaurant restaurant1 = restaDao.selectByid(restaurant.getrId());
            String pass=restaurant1.getPass();
            if (pass == null) {
                model.addAttribute("erro", "请注册用户！");
                return "login";
            }
            Menu menu = new Menu();
            model.addAttribute(menu);
            if (pass.equals(restaurant.getPass())) {
                model.addAttribute("RestaId", "restaurant");
                System.out.println("dsads:"+restaurant1.getAddress());
//放入缓存
                hashOperations=redisTemplate.opsForHash();
                hashOperations.put("mysession","mysession",restaurant1);
                model.addAttribute("topimg",hashOperations.get("mysession","mysession").getAddress());
                model.addAttribute("rome","all");
                return "index";
            } else {
                model.addAttribute("erro", "用户名或密码错误");
                return "index";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("erro", "请注册用户！");
            return "index";
        }

    }
    @RequestMapping("/login/upload")
    public  String upload(Model model){
        try {
            Restaurant restaurant=new Restaurant();
            Menu menu = new Menu();
            model.addAttribute(menu);
            model.addAttribute("RestaId", "restaurant");
            model.addAttribute(restaurant);
            hashOperations = redisTemplate.opsForHash();
            if (hashOperations.hasKey("mysession", "mysession")) {
                model.addAttribute("topimg",hashOperations.get("mysession","mysession").getAddress());
                return "upload";
            } else {
                model.addAttribute("erro1", "登录后才能上传菜单！");
                return "index";
            }
        }catch (Exception e){

            return "index";
        }

    }

    @PostMapping("/register")
    public  String  register(@ModelAttribute Restaurant restaurant,Model model,@RequestParam("file") MultipartFile file){
        // 判断文件是否为空
        String url="";
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String s = uuid.toString();
            String menuid = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
            try {
                byte[] bytes = file.getBytes();
                File f = new File("E:\\images\\");
                if (!f.exists()) {
                    f.mkdirs();
                }
                String path = f.getCanonicalPath() + "/" + filename;
                File file1 = new File(path);
                FileOutputStream outputStream = new FileOutputStream(file1);
                outputStream.write(bytes);
                outputStream.close();
                url = ossclientUtilDao.fileUplodnew(path, filename, "img/ygy/");
                file1.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Restaurant newre=new Restaurant();
        newre.setAddress(url);
        newre.setrId(restaurant.getrId());
        newre.setName(restaurant.getName());
        newre.setrPhone(restaurant.getrPhone());
        newre.setPass(restaurant.getPass());

                try {
              restaDao.addResta(newre);
                }catch (Exception e){
                    model.addAttribute("erro1","该用户已经注册");
                }
//        model.addAttribute("topimg",newre.getAddress());
//                model.addAttribute("iftop.class",".iftop");

        return "index";
    }


    @GetMapping("/hello")
    public String hello(Model model){
        Restaurant restaurant=new Restaurant();
//        User user=new User();
//        model.addAttribute(user);
        model.addAttribute(restaurant);
        model.addAttribute("rome","all");
        HashOperations<String,String,Restaurant> hashOperations=redisTemplate.opsForHash();
        if (!hashOperations.hasKey("mysession","mysession")){

            return "index";
        }
        model.addAttribute("topimg",hashOperations.get("mysession","mysession").getAddress());
        return "index";
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
