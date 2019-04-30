package com.ygy.dao;

import com.ygy.mapper.CustomerMapper;
import com.ygy.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class Testygy {
    @Autowired
    public CustomerMapper customerMapper;
    @Autowired
    public RedisTemplate redisTemplate;
    static HashOperations<String,String,String> operations1;
    public Customer select(){
        return  customerMapper.select(1);
    }
    public void  add(){
        operations1=redisTemplate.opsForHash();
        operations1.put("5","ygy","123456");
    }
    public String  get(){
       return operations1.get("5","ygy");
    }
}
