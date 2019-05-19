package com.ygy.dao;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.ygy.mapper.MenuMapper;
import com.ygy.mapper.RestaurantMapper;
import com.ygy.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ygy
 * @date 2019/5/17 10:10
 */
@Service
@Order(1)
public class SvdDaoImpl implements  SvdDao, CommandLineRunner {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MenuMapper menuMapper;
    static HashOperations<String,String, List<String>> operations;
    static HashOperations<String,String,Integer> useroperations;

    //程序启动时自动生成menu表,m_name表（用于存各个菜的相似度）
    @Override
    public void run(String... strings) throws Exception {
        operations=redisTemplate.opsForHash();
        useroperations=redisTemplate.opsForHash();
        List<Menu> list = menuMapper.selectByrid("restaurant");
        for (Menu menu : list) {
            //当hashkey没有时才添加数据
            operations.putIfAbsent("menu", menu.getmName(), null);
            for (Menu menu1:list){

                if (menu.getmName().equals(menu1.getmName())){
                    continue;
                }
                useroperations.putIfAbsent(menu.getmName().getBytes().toString(),menu1.getmName().getBytes().toString(),2);
                System.out.println(menu.getmName()+":" +menu1.getmName());

            }
        }
        System.out.println(useroperations.get("酸辣白菜".getBytes().toString(),"韭菜炒鸡蛋".getBytes().toString()));
        System.out.println("redis 创建mean表");
    }



    @Override
    public void add(String mname,List<String> openidlist){
        operations=redisTemplate.opsForHash();
        if (!operations.hasKey("menu",mname)) {
            ArrayList<String> str=new ArrayList<java.lang.String>();
            str.add(mname);
            operations.putIfAbsent("menu", mname, str);

        }else {
            openidlist.add(mname);
            operations.put("menu",mname, openidlist);
        }
    }
    @Override
    public boolean addOpidTable(String openid) {
        try {
            useroperations = redisTemplate.opsForHash();
            List<Menu> list = menuMapper.selectByrid("restaurant");
            for (Menu menu : list) {
                //当hashkey没有时才添加数据
                useroperations.putIfAbsent(openid, menu.getmName(), 0);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public double Calculation(String name1,String name2) {
       List<String> list1=operations.get("menu",name1);
       List<String> list2=operations.get("menu",name2);
       List<Integer> numlist1=new ArrayList<Integer>();
        List<Integer> numlist2=new ArrayList<Integer>();
        int sum=0;
        if (list1.size()<list2.size()){
            List<String> list=list1;
            list1=list2;
            list2=list;
        }
        for (String s:list1){
            int num1=useroperations.get(s,name1);
            int num2=useroperations.get(s,name2);
            sum+=js(num1,num2);
        }
       double sqrt= Math.sqrt(sum);
        return sqrt/(1+sqrt);
    }
    public int js(int num1,int num2){
        int sum=0;
        sum=num1*num1+num2*num2;
        return sum;
    }

public HashMap<String,Integer> sort(HashMap<String,Integer> map){
    List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
    list.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
    HashMap<String,Integer> restle=new HashMap<>();
    for (Map.Entry<String, Integer> mapping : list){
        System.out.println(mapping.getKey()+": "+mapping.getValue());
        restle.put(mapping.getKey(),mapping.getValue());
    }
    return restle;
}

    @Override
    public void addMnameRedis(String m_name,List<String> list) {
        for (String str:list){
          int num=useroperations.get(m_name,str);
            useroperations.put(m_name,str,num+1);
        }

    }
}
