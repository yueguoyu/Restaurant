package com.ygy.controller;




import com.ygy.mapper.TOrderMapper;
import com.ygy.model.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ygy
 * @date 2019/4/9 15:01
 */
@Controller
public class orderControlle {
    @Autowired
    TOrderMapper tOrderMapper;
    @RequestMapping("/selectOrder")
    @Scheduled(fixedRate = 60000)
    public String selectOrder(Model model){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
      String newtime=  df.format(new Date());// new Date()为获取当前系统时间
        System.out.println(newtime);
   List<TOrder> list=tOrderMapper.selectByTime(newtime.substring(0,newtime.length()-5));
        Set<String> set=new HashSet<>();
        for (TOrder t:list){
            set.add(t.getOrdernumber());
        }
        List<TOrder> re=new ArrayList<>();
        for (String str:set){
            TOrder neworder=new TOrder();
            String name="";
            String detail="";
          for (TOrder t1:tOrderMapper.selectByOpenid(str)){
             name=name+"   "+t1.getName();
              neworder.setOrdernumber(t1.getOrdernumber());
            detail=detail+"   "+t1.getDetail();
            neworder.setTime(t1.getTime());
            neworder.setId(t1.getId());
          }
          neworder.setName(name);
          neworder.setDetail(detail);

          re.add(neworder);
        }
//   model.addAttribute("list1",tOrderMapper.selectByTime("2019/05/22 21"))  ;
    model.addAttribute("list",re);
    return "order";
    }
    @RequestMapping("/selectOrder/select")
    public String selectOrderByorNumber(String title,Model model){
        System.out.println(title);
       List<TOrder> re=tOrderMapper.selectByNumber(title);
        model.addAttribute("list",re);
        return "order";
    }


}
