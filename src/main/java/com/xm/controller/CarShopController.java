package com.xm.controller;


import com.xm.entity.CarShop;
import com.xm.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarShopController {

    @Autowired
    private CarShopService carShopService;

    @GetMapping("/addcarshop")
    public String addCarshop(CarShop carShop) {
        CarShop shop = carShopService.getCarShopByCustomerId(carShop);
        if(shop != null){
            carShopService.updateCarShopAdd(carShop);
        }else {
            carShopService.addCarshop(carShop);
        }

        //取出当前购物车信息
        return "redirect:/showcarshopbycustomerid?customerid=" + carShop.getCustomerid();
    }

    @GetMapping("/getExistGoodsNumber")
    @ResponseBody
    public Map<String, Object> getExistGoodsNumber(CarShop carShop){
        CarShop cs = carShopService.getCarShopByCustomerId(carShop);
        Map<String, Object> map = new HashMap<>();
        if(cs != null){
            map.put("num", cs.getNumbers());
        }else {
            map.put("num", 1);
        }
        return map;
    }

    @GetMapping("/showcarshopbycustomerid")
    public String showCarShop(int customerid, Model model){
        List<HashMap<String, Object>> carList = carShopService.getShopCar(customerid);
        model.addAttribute("carlist", carList);
        return "carshop";
    }

    @GetMapping("/deletecarshop")
    public String delCarShop(int cid, int customerid){
        carShopService.delCarShop(cid, customerid);
        return "redirect:/showcarshopbycustomerid?customerid=" + customerid;
    }

    @GetMapping("/changenumber")
    public String updateCarShopById(int cid, int num, int customerid){
        carShopService.updateCarShopById(cid, num);
        return "redirect:/showcarshopbycustomerid?customerid=" + customerid;
    }

}


