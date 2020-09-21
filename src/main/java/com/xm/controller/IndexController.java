package com.xm.controller;


//用于商品主页面的控制器

import com.xm.entity.Product;
import com.xm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/front")
public class IndexController {

    @Autowired
    private ProductService productService;

    //首页显示前五条数据
    @GetMapping("/index")
    public String toShopPage(Model model){

        List<Product> products=productService.getProductFiveList();
        model.addAttribute("products",products);
        return "shop";

    }

}
