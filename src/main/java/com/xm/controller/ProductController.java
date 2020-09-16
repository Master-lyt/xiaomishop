package com.xm.controller;

import com.xm.entity.PageBean;
import com.xm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/getprobypage")
    public String showProduct(@RequestParam(name = "page", defaultValue = "1") int page, Model model){
        int pagesize = 5;
        PageBean<HashMap<String, Object>> products = (PageBean<HashMap<String, Object>>) productService.getAllProductByPage(page, pagesize);
        model.addAttribute("products", products);
        //去页面product.jsp
        return "product";//WEB-INF/jsp/productnopage.jsp
    }

}
