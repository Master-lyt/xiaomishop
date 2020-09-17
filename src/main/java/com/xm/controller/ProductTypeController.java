package com.xm.controller;


import com.xm.entity.PageBean;
import com.xm.entity.ProductType;
import com.xm.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
*此处需要用到mybatis反向工具 并在producttype处使用ajax实现动态页面
* */

@Controller
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/toproducttypepage")
    public String showProductTypeByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                        @RequestParam(name = "typename", defaultValue = "") String typename, Model model){
        int pagesize = 5;
        PageBean<ProductType> products = productTypeService.selectProductTypeByPage(page, typename, pagesize);
        List<ProductType> list = products.getList();
        model.addAttribute("pagebean", products);
        model.addAttribute("typename", typename);
        return "producttypenoajax";
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
        if(productTypeService.deleteProductTypeById(id) != 1){
            //放到 ajax
            return "redirect:/toproducttypepage";
        }else {
            return "redirect:/toproducttypepage";
        }
    }

    @GetMapping("/toupdateprotypepage")
    public String toUpdateProductTypePage(int id, Model model){
        ProductType productType = productTypeService.selectProductTypeById(id);
        model.addAttribute("producttype",productType);
        return "updateproducttype";
    }

    @PostMapping("/updateprotype")
    public String updateProductType(ProductType productType){
        productTypeService.updateNonEmptyProductTypeById(productType);
        return "redirect:/toproducttypepage";
    }

}
