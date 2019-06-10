package com.ygy.controller;




import com.ygy.mapper.TOrderMapper;
import com.ygy.model.Restaurant;
import com.ygy.model.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


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
    RedisTemplate redisTemplate;
    @Autowired
    TOrderMapper tOrderMapper;
    /**
    * @Description:  每分钟更新订单页面
    * @Param: []
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: Mr.Wang
    * @Date: 2019/5/25
    */

    @RequestMapping("/selectOrder1")
    @Scheduled(fixedRate = 60000)
    public ModelAndView selectOrder1(){
        System.out.println("selectOrder定时任务");
        return new ModelAndView("/selectOrder");
    }
    @RequestMapping("/selectOrder")
//    @Scheduled(fixedRate = 60000)
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
            int sum=0;
          for (TOrder t1:tOrderMapper.selectByOpenid(str)){
             name=name+"       "+t1.getName()+"          "+t1.getNumber();
              neworder.setOrdernumber(t1.getOrdernumber());
            detail=detail+"         "+t1.getDetail()+"    +      "+t1.getRemarks();
            neworder.setTime(t1.getTime());
            neworder.setId(t1.getId());
              sum=sum+(Integer.parseInt(t1.getPrice())*Integer.parseInt(t1.getNumber()));

          }
          neworder.setName(name);
          neworder.setDetail(detail);
         neworder.setPrice(Integer.toString(sum));
            neworder.setRemarks("");
          re.add(neworder);
        }
//   model.addAttribute("list1",tOrderMapper.selectByTime("2019/05/22 21"))  ;
        HashOperations<String,String,Restaurant> hashOperations=redisTemplate.opsForHash();
        try {
            model.addAttribute("topimg", hashOperations.get("mysession", "mysession").getAddress());
        }catch (Exception e){

        }
        Collections.sort(re,new Comparator<TOrder>(){
            @Override
            public int compare(TOrder o1, TOrder o2) {
                return o2.getTime().compareTo(o1.getTime());}});
        model.addAttribute("list",re);
    return "order";
    }
    @RequestMapping("/selectOrder/select")
    public String selectOrderByorNumber(String title,Model model){
        System.out.println(title);
       List<TOrder> re1=tOrderMapper.selectByNumber(title);
        Set<String> set=new HashSet<>();
        for (TOrder t:re1){
            set.add(t.getOrdernumber());
        }
        List<TOrder> re=new ArrayList<>();
        for (String str:set){
            TOrder neworder=new TOrder();
            String name="";
            String detail="";
            int sum=0;
            for (TOrder t1:tOrderMapper.selectByOpenid(str)){
                name=name+"       "+t1.getName()+"          "+t1.getNumber();
                neworder.setOrdernumber(t1.getOrdernumber());
                detail=detail+"         "+t1.getDetail()+"      +    "+t1.getRemarks();
                neworder.setTime(t1.getTime());
                neworder.setId(t1.getId());
                sum=sum+(Integer.parseInt(t1.getPrice())*Integer.parseInt(t1.getNumber()));

            }
            neworder.setName(name);
            neworder.setDetail(detail);
            neworder.setPrice(Integer.toString(sum));
            re.add(neworder);
        }
        HashOperations<String,String,Restaurant> hashOperations=redisTemplate.opsForHash();
        model.addAttribute("topimg",hashOperations.get("mysession","mysession").getAddress());
//5.28
        Collections.sort(re,new Comparator<TOrder>(){
            @Override
            public int compare(TOrder o1, TOrder o2) {
                return o2.getTime().compareTo(o1.getTime());}});
        model.addAttribute("list",re);
        return "order";
    }
    @RequestMapping("/selectOrderAll")
    public String selectOrderall(Model model) {
        List<TOrder> list=tOrderMapper.selectAll();
        Set<String> set=new HashSet<>();
        for (TOrder t:list){
            set.add(t.getOrdernumber());
        }
        List<TOrder> re=new ArrayList<>();
        for (String str:set){
            TOrder neworder=new TOrder();
            String name="";
            String detail="";
            int sum=0;
            for (TOrder t1:tOrderMapper.selectByOpenid(str)){
                name=name+"       "+t1.getName()+"          "+t1.getNumber();
                neworder.setOrdernumber(t1.getOrdernumber());
                detail=detail+"         "+t1.getDetail()+"      +    "+t1.getRemarks();
                neworder.setTime(t1.getTime());
                neworder.setId(t1.getId());
                sum=sum+(Integer.parseInt(t1.getPrice())*Integer.parseInt(t1.getNumber()));

            }
            neworder.setName(name);
            neworder.setDetail(detail);
            neworder.setPrice(Integer.toString(sum));
            re.add(neworder);
        }
        HashOperations<String,String,Restaurant> hashOperations=redisTemplate.opsForHash();
        try {
            model.addAttribute("topimg", hashOperations.get("mysession", "mysession").getAddress());
        }catch (Exception e){

        }
        Collections.sort(re,new Comparator<TOrder>(){
                    @Override
                    public int compare(TOrder o1, TOrder o2) {
                        return o2.getTime().compareTo(o1.getTime());}});
        model.addAttribute("list",re);
        return "order";
    }
}
