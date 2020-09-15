package com.xm.controller;

import com.xm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

/**
 * @author lz
 * @date 2020/9/15 - 15:59
 * @function
 */
@Controller
public class ProductController {

    //需要注入业务层
    @Autowired
    private ProductService productService;

    //获取所有的商品的信息
    @GetMapping("/getproduct")
    public String getAllProducts(Model model){
        List<HashMap<String,Object>> products = productService.getProducts();
        model.addAttribute("products", products);
        //去页面productnopage.jsp
        return "productnopage";//WEB-INF/jsp/productnopage.jsp
    }

}
