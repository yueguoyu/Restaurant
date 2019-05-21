package com.ygy.common;


import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygy.model.Myorder;
import com.ygy.model.TOrder;
import com.ygy.model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @Title: JsonUtils.java
 * @Package com.lee.utils
 * @Description: 自定义响应结构, 转换类
 * Copyright: Copyright (c) 2016
 * Company:Nathan.Lee.Salvatore
 * 
 * @author leechenxiang
 * @date 2016年4月29日 下午11:05:03
 * @version V1.0
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    /** 将json转换成对象
    * @Description:
    * @Param: [json]
    * @return: void
    * @Author: ygy
    * @Date: 2019/5/21
    */
    public static   List<Myorder>   myjsonList(String json){
        JSONArray jsonArray = JSONArray.parseArray(json);
        ArrayList<Myorder> users = new ArrayList<Myorder>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Myorder user=new Myorder();
            user.setName(jsonArray.getJSONObject(i).getString("name"));
            user.setDetail(jsonArray.getJSONObject(i).getString("detail"));
            user.setPrice(jsonArray.getJSONObject(i).getString("price"));
            user.setSum(jsonArray.getJSONObject(i).getString("sum"));
            user.setNumber(jsonArray.getJSONObject(i).getString("number"));
            users.add(user);
        }
        return users;
    }
    
}
