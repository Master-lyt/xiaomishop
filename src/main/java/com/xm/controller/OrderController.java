package com.xm.controller;

import com.xm.entity.PageBean;
import com.xm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/getorderbypage")
    public String getAllOrder(@RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name = "name", defaultValue = "") String name,
                              @RequestParam(name = "typeId", defaultValue = "-1") int typeId, Model model){
        int pagesize = 5;
        PageBean<HashMap<String, Object>> allOrder = orderService.getAllOrder(name, typeId, page, pagesize);
        model.addAttribute("pagebean", allOrder);
        return "orderbypage";
    }
}
