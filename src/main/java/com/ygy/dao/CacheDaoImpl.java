package com.ygy.dao;

import com.ygy.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ygy
 * @date 2019/4/3 15:32
 */
@Service
public class CacheDaoImpl implements  CacheDao {

    @Autowired
    private RedisTemplate redisTemplate;
    static HashOperations<String,String, Menu> hashOperations;
    static SetOperations<String,Menu> setOperations;
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
    public  Menu selectMenuByCache(String rid,String mid) {
        if (hashOperations==null){
            hashOperations=redisTemplate.opsForHash();
        }

        if(hashOperations.hasKey(rid,mid)){
           return hashOperations.get(rid,mid);
        }else {
         Menu menu= menuDao.selectByidandmid(rid,mid);
            addMenuCache(rid,menu);
            return menu;
        }
    }

    @Override
    public List<Menu> selectMenuAll(String rid) {
        hashOperations=redisTemplate.opsForHash();
      List<Menu> list=hashOperations.values(rid);
        return list;
    }

    @Override
    public void addOrder(String uid, Menu menu) {
        setOperations=redisTemplate.opsForSet();


    }
}
