package com.xm.controller;


import com.xm.entity.PageBean;
import com.xm.entity.ProductType;
import com.xm.form.ProductTypeForm;
import com.xm.service.ProductTypeService;
import com.xm.untils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*此处需要用到mybatis反向工具 并在producttype处使用ajax实现动态页面
* */
@Controller
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/toproducttypepage")
    public String showProductTypeByPage(){
        return "producttype";
    }

    @GetMapping("/producttype_list_ajax")
    @ResponseBody
    public Map<String, Object> getAllProductTypeByPage(@RequestParam Map<String, Object> map){
        Integer pn = Integer.parseInt((String) map.get("pn"));
        Query query = new Query(pn, 5);
        Integer typeId = Integer.parseInt((String) map.get("typeId"));
        String typeName =  (String) map.get("typeName");
        ProductType productType = new ProductType(typeId, typeName);
        PageBean<ProductType> products = productTypeService.selectProductTypeByPage(productType, query);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", products.getList());
        resultMap.put("pageSize", query.getPs());
        resultMap.put("pageCount", products.getPages());
        resultMap.put("rowCount", products.getRowcount());
        resultMap.put("tid", typeId);
        resultMap.put("tname", typeName);
        return resultMap;
    }

    @GetMapping("/addproducttypepage")
    public String toAddProductTypePage(){
        return "addproducttype";
    }

    @PostMapping("/addprotype")
    public String addProductType(ProductType productType){
        System.out.println(productType);
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

    @GetMapping("/producttypemodify")
    public String toUpdateProductTypePage(int id, Model model){
        ProductType productType = productTypeService.selectProductTypeById(id);
        model.addAttribute("producttype",productType);
        return "updateproducttype";
    }

    @PostMapping("/updateprotype")
    public String updateProductType(ProductType productType){
        System.out.println("abc:" + productType);
        productTypeService.updateProductTypeById(productType);
        return "redirect:/toproducttypepage";
    }

}
