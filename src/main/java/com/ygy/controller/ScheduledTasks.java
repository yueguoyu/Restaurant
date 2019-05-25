package com.ygy.controller;

import com.ygy.dao.MenuDao;
import com.ygy.dao.SvdDao;
import com.ygy.mapper.MenuMapper;
import com.ygy.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 每天早上3点进行相似度计算和推荐
 * @author ygy
 * @date 2019/5/17 13:57
 */
@Component
public class ScheduledTasks {
    @Autowired
    SvdDao svdDao;
    @Autowired
    MenuDao menuDao;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MenuMapper menuMapper;
    @Scheduled(cron ="0 0 3 * * ?")
    public void similarity(){
      List<Menu> list=menuDao.selectByrid("restaurant");
      for (Menu m:list){
          for (Menu m1:list){
              if (m.getmName().equals(m1.getmName())){
                  continue;
              }else {
                double num=svdDao.Calculation(m.getmName(),m1.getmName());
                    svdDao.addsimilarity(m.getmName(),m1.getmName(),num);
              }
          }
      }
    }
//    每天的0点、13点、18点、21点都执行一次
    @Scheduled(cron = "0 0 0,14,18,21 * * ?")
    public void selctallSched(){
        HashOperations<String,String,List<Menu>> hashOperations=redisTemplate.opsForHash();
        List<Menu> list=menuMapper.selectByrid("restaurant");
        hashOperations.put("restaurant","restaurant",list);
        System.out.println("定时任务执行");
    }

}
