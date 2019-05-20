package com.ygy.controller;




import com.ygy.mapper.TOrderMapper;
import com.ygy.model.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.jws.WebParam;
import java.util.List;

/**
 * @author ygy
 * @date 2019/4/9 15:01
 */
@Controller
public class orderControlle {
    @Autowired
    TOrderMapper tOrderMapper;
    @RequestMapping("/selectOrder")
    public String selectOrder(Model model){
   List<TOrder> list=tOrderMapper.selectAll();
    model.addAttribute("list",list);
    return "order";
    }


}
