package com.ygy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author ygy
 * @date 2019/5/17 17:35
 */
@Service
public class Recommend {
    @Autowired
    SvdDao svdDao;
    public List<String> recommend(){
        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();

        return null;
    }
}
