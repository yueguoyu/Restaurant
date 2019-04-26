package com.ygy.dao;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author ygy
 * @date 2018/5/16
 * 阿里云oss对象存储工具
 */
public interface OssclientUtilDao {
    /**
     * @param key
     * @param file
     */
    void uplod(String key, MultipartFile file);

    /**
     * 有时间权限的图片文件返回url
     *
     * @param
     * @param
     * @return
     */
    String getUrl(String key);

    /**
     * 获得没有时间权限图片
     *
     * @param file
     * @return
     */
    String getSimpleUrl(String username, MultipartFile file);

    /**
     * @param bucketName
     */
    void creatBucket(String bucketName);

    /**
     * @param bucketName
     * @return
     */
    boolean deleteBucket(String bucketName);

    /**
     * @param key
     * @return
     */
    String getSignedUrl(String key);

    /**
     * @param str
     */
    void fileUplod(String filename, String str, String key);
    /**
    * @Description:  将图片传到阿里云并返回url
    * @Param: [fileStr, filename]   fileUrl阿里云中文件地址 如：/&&/&&/
    * @return: java.lang.String
    * @Author: ygy
    * @Date: 2019/4/26
    */
    String fileUplodnew(String fileStr,String filename,String fileUrl);
}