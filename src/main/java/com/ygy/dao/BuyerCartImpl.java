package com.ygy.dao;

import com.ygy.model.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 购物车
 */
@Service
public class BuyerCartImpl implements BuyerCart {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 使用散列存储了商品名称（id）与商品信息的映射
     */
    static HashOperations<String, String, LineItem> operations;

    /**
     * 向购物车中增加商品
     *
     * @param username （sessionid，散列的key）
     * @param lineItem 商品信息类
     */
    @Override
    public void add(String username, LineItem lineItem) {
        operations = redisTemplate.opsForHash();
        //用户没有购物车
        if (!redisTemplate.hasKey(username)) {
            operations.put(username, lineItem.getName(), lineItem);
        } else {
            //有该商品
            if (operations.hasKey(username, lineItem.getName())) {
                int num = operations.get(username, lineItem.getName()).getQuantity() + lineItem.getQuantity();
                LineItem item = new LineItem();
                item.setName(lineItem.getName());
                item.setPrice(lineItem.getPrice());
                item.setQuantity(num);
                operations.put(username, lineItem.getName(), item);
            } else {
                LineItem item = new LineItem();
                item.setName(lineItem.getName());
                item.setPrice(lineItem.getPrice());
                item.setQuantity(lineItem.getQuantity());
                operations.put(username, lineItem.getName(), item);
            }
        }
    }

    /**
     * 根据username（Sessionid）获取购物车中所有的信息
     *
     * @param username （sessionid，散列的key）
     * @return List<LineItem>
     */
    @Override
    public List<LineItem> getall(String username) {
        //获取购物车中全部的信息
        Map<String, LineItem> map = operations.entries(username);
        Set<String> set = map.keySet();
        List<LineItem> list = new LinkedList<LineItem>();
        for (String key : set) {
            list.add(map.get(key));
        }
        return list;
    }

    /**
     * 得到购物车中所有商品的总价值
     *
     * @return long 购物车购物车中所有商品的总价值
     */
    @Override
    public long getCartTotal(String username) {
        long sum = 0;
        for (Map.Entry<String, LineItem> entry : operations.entries(username).entrySet()) {
            LineItem item = entry.getValue();
            long num = item.getPrice() * item.getQuantity();
            sum += num;
        }
        return sum;
    }

    /**
     * 删除购物车中商品
     * @param username
     * @param lineItem
     */
    @Override
    public void dele(String username, LineItem lineItem) {
        if (redisTemplate.hasKey(username)){
            //购物车中有这个商品
            if (operations.hasKey(username,lineItem.getName())){
                //购物车中的数量
                int numold=operations.get(username,lineItem.getName()).getQuantity();
                //要删除的数
                int num=lineItem.getQuantity();
                LineItem item=new LineItem();
                item.setName(lineItem.getName());
                item.setPrice(lineItem.getPrice());
                item.setQuantity(numold-num);
                if (numold>=num) {
                    operations.put(username, item.getName(), item);
                }
            }
        }
    }
}