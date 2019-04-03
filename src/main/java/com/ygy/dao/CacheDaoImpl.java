package com.ygy.dao;

import com.ygy.model.Menu;
import com.ygy.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ygy
 * @date 2019/4/3 15:32
 */
@Service
public class CacheDaoImpl implements  CacheDao {

    @Autowired
    private RedisTemplate redisTemplate;
   static HashOperations<String,String, Menu> hashOperations;
    @Override
    public boolean addMenuCache(String rid,Menu menu) {
        hashOperations=redisTemplate.opsForHash();
        if (menu!=null&&rid!=null){
         return  hashOperations.putIfAbsent(rid,menu.getMenuId(),menu);
        }
        return false;
    }
    /**
    * @Description: 每个菜的详细数据
    * @Param: [rid, mid]
    * @return: com.ygy.model.Menu
    * @Author: ygy
    * @Date: 2019/4/3
    */
    @Autowired
    MenuDao menuDao;
    @Override
    public  Menu SelectMenuByCache(String rid,String mid) {
        Menu restmenu=null;
        if(hashOperations.hasKey(rid,mid)){
           restmenu=hashOperations.get(rid,mid);
        }else {
        Menu menu =menuDao.selectByidandmid(rid,mid);
            hashOperations.put(rid,mid,menu);
        }
        return restmenu;
    }

    @Override
    public List<Menu> selectMenuAll(String rid) {
      List<Menu> list=hashOperations.values(rid);
        return list;
    }
}
