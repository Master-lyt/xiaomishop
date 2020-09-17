package com.xm.controller;


import com.xm.entity.ProductType;
import com.xm.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
*此处需要用到mybatis反向工具 并在producttype处使用ajax实现动态页面
* */

@Controller
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/toproducttypepage")
    public String showProductType(Model model){
        List<ProductType> productTypes = productTypeService.selectProductType();
        model.addAttribute("ptlist",productTypes);
        return "producttype";
    }

    @GetMapping("/addproducttypepage")
    public String toAddProductTypePage(){
        return "addproducttype";
    }

    @PostMapping("/addprotype")
    public String addProductType(ProductType productType, Model model){
        if(productTypeService.insertProductType(productType) != 1){
            //放到 ajax
            return "redirect:/toproducttypepage";
        }else {
            return "redirect:/toproducttypepage";
        }
    }

    @GetMapping("/delproducttype")
    public String deleteProductType(int id){
        System.out.println("id:" + id);
        if(productTypeService.deleteProductTypeById(id) != 1){
            //放到 ajax
            return "redirect:/toproducttypepage";
        }else {
            return "redirect:/toproducttypepage";
        }
    }

}
