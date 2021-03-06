package com.ygy.controller;

import com.ygy.dao.CacheDao;
import com.ygy.dao.MenuDao;
import com.ygy.dao.OssclientUtilDao;

import com.ygy.mapper.MenuMapper;
import com.ygy.model.Menu;
import com.ygy.model.Restaurant;
import net.minidev.json.JSONObject;

import org.apache.spark.sql.catalyst.expressions.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author ygy
 * @date 2019/4/4 14:58
 */
@Controller
public class menuController {
    @Autowired
    CacheDao cacheDao;
    @Autowired
    MenuDao menuDao;
    @Autowired
    OssclientUtilDao ossclientUtilDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
     RedisTemplate redisTemplate;
    @Autowired
    MenuMapper menuMapper;
    HashOperations<String,String, Restaurant> hashOperations;
    @PostMapping("/addMenu/{id}")
    public String  addMenu(HttpServletRequest request, @RequestParam("file") MultipartFile file, @ModelAttribute Menu menu, @PathVariable("id") String rid, Model model)  {
        // 判断文件是否为空
        String url="";
        String mrid=rid;
        if (!file.isEmpty()){
            String filename=  file.getOriginalFilename();
            UUID uuid=UUID.randomUUID();
            String s=uuid.toString();
            String menuid= s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
            try {
                byte[] bytes= file.getBytes();
                File f=new File("E:\\images\\");
                if (!f.exists()){
                    f.mkdirs();
                }
                String path=f.getCanonicalPath()+"/"+filename;
                File file1=new File(path);
                FileOutputStream outputStream  =new FileOutputStream(file1);
                outputStream.write(bytes);
                outputStream.close();
                url= ossclientUtilDao.fileUplodnew(path,filename,"img/ygy/");
                file1.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            menu.setRestaId(mrid);
            menu.setMenuId(menuid);
            menu.setImgurl(url);
        }else {
            model.addAttribute("continue","输入不能为空");
        }
        menuDao.addMenu(menu);
        model.addAttribute("continue","请继续添加，如果添加完请关闭网页！");
        model.addAttribute("RestaId", rid);
        return "upload";

    }

    @RequestMapping("/menuSelect")
    public String menuSelect(String rid,String mid){
        System.out.println(cacheDao.selectMenuByCache("1","1"));
       return "000";
    }
    @GetMapping("/selectByrid")
    @ResponseBody
    public List<Menu> selectByrid(String rid){
        JSONObject json=new JSONObject();
          List<Menu> list=menuDao.selectByrid("restaurant");
      json.put("list",list);
        return list;
    }
    @GetMapping("/selectByrid/recommend")
    @ResponseBody
    public List<Menu> Recommendmenu(){
        ZSetOperations<String,String> zsetOperations=redisTemplate.opsForZSet();
        Set<String> set= zsetOperations.reverseRange("recommendMenu",Integer.MIN_VALUE,Integer.MAX_VALUE);
        List<Menu> menuList= new ArrayList<Menu>();
        for (String menuname:set){
            menuList.add(menuMapper.selectByname(menuname));
        }
        JSONObject json=new JSONObject();

        json.put("list",menuList);
        return menuList;
    }

    @GetMapping("/selectmenuPc")
    public String selectmenuPc(Model model){
        Menu menu=new Menu();
        model.addAttribute(menu);
        List<Menu> list=menuDao.selectByrid("restaurant");
        model.addAttribute("list",list);
        hashOperations=redisTemplate.opsForHash();
        if (hashOperations.hasKey("mysession","mysession")){
            model.addAttribute("topimg",hashOperations.get("mysession","mysession").getAddress());
        }else {

            model.addAttribute(new Restaurant());
            model.addAttribute("erro1","请登录");

            return "index";
        }
        return "menu";
    }
    @PostMapping("/add")
    @ResponseBody
    public  String add(@ModelAttribute Menu menu){
        String mname=menu.getmName();
        return mname;
    }
    @GetMapping("/deletemenu")
    public String delete(String title,Model model){

        if (hashOperations.hasKey("mysession","mysession")){
            model.addAttribute("topimg",hashOperations.get("mysession","mysession").getAddress());
        }else {
            model.addAttribute(new Restaurant());
            model.addAttribute("erro1","请登录");
            return "index";
        }
           menuDao.deleMenuByname(title);
        List<Menu> list=menuDao.selectByrid("restaurant");
        model.addAttribute("list",list);
        System.out.println("hahhah"+title);
        hashOperations=redisTemplate.opsForHash();
        model.addAttribute(new Menu());
        return "menu";
    }
    @RequestMapping("/updatemenu1")
    public String update(@RequestParam("file") MultipartFile file, @ModelAttribute Menu menu,Model model){
        // 判断文件是否为空
        String url="";
        if (!file.isEmpty()){
            String filename=  file.getOriginalFilename();
            try {
                byte[] bytes= file.getBytes();
                File f=new File("E:\\images\\");
                if (!f.exists()){
                    f.mkdirs();
                }
                String path=f.getCanonicalPath()+"/"+filename;
                File file1=new File(path);
                FileOutputStream outputStream  =new FileOutputStream(file1);
                outputStream.write(bytes);
                outputStream.close();
                url= ossclientUtilDao.fileUplodnew(path,filename,"img/ygy/");
                file1.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            menu.setImgurl(url);

        }
        Menu menu1=menuDao.SelectByName(menu.getmName());
        if (menu1!=null){
            String mid=menu1.getMenuId();
            menu.setMenuId(mid);
            menuDao.updateMenu(menu);
        }else {
            model.addAttribute("erro4","请重新输入");
        }
        List<Menu> list=menuDao.selectByrid("restaurant");
        model.addAttribute("list",list);

        hashOperations=redisTemplate.opsForHash();
        if (hashOperations.hasKey("mysession","mysession")){
            model.addAttribute("topimg",hashOperations.get("mysession","mysession").getAddress());
        }else {
            model.addAttribute(new Restaurant());
            model.addAttribute("erro1","请登录");

            return "index";
        }

        return "menu";
    }



}
