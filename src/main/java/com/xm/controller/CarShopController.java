package com.xm.controller;


import com.xm.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class CarShopController {

    @Autowired
    private CarShopService carShopService;

    @GetMapping("/addcarshop")
    public String addCarshop(int customerid, int pid, String numbers) {

        carShopService.addCarshop(customerid, pid, numbers);
        //取出当前购物车信息
        return "redirect:/showcarshopbycustomerid?customerid=" + customerid;
    }
}


