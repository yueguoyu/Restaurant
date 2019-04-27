package com.ygy.controller;

import com.ygy.dao.CacheDao;
import com.ygy.dao.MenuDao;
import com.ygy.dao.OssclientUtilDao;
import com.ygy.mapper.MenuMapper;
import com.ygy.model.Menu;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author ygy
 * @date 2019/4/4 14:58
 */
@RestController
public class menuController {
    @Autowired
    CacheDao cacheDao;
    @Autowired
    MenuDao menuDao;
    @Autowired
    OssclientUtilDao ossclientUtilDao;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/menuSelect")
    public String menuSelect(String rid,String mid){
        System.out.println(cacheDao.selectMenuByCache("1","1"));
       return "000";
    }
    @GetMapping("/selectByrid")
    @ResponseBody
    public List<Menu> selectByrid(String rid){
        JSONObject json=new JSONObject();
      List<Menu> list=menuDao.selectByrid("002");
      json.put("list",list);
        return list;
    }
    @PostMapping("/addMenu")
    @ResponseBody
    public void  addMenu(@RequestParam("file") MultipartFile file){
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
             File file1=new File("C:\\Users\\ygy\\Pictures\\images");
                FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
//               String url= ossclientUtilDao.fileUplodnew(filePath,"yy1.jpg","img/ygy/");
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
            }
        }

    }
}
