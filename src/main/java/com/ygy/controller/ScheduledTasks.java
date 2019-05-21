package com.ygy.controller;

import com.ygy.dao.MenuDao;
import com.ygy.dao.SvdDao;
import com.ygy.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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
}
