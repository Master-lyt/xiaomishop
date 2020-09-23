package com.xm.controller;


import com.xm.entity.PageBean;
import com.xm.entity.ProductType;
import com.xm.service.ProductTypeService;
import com.xm.untils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

    @PostMapping("/producttype_list_ajax")
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

    @PostMapping("/addprotype")
    @ResponseBody
    public Map<String, Object> addProductType(ProductType productType){
        Map<String, Object> map = new HashMap<>();
        String message = "";
        if(productTypeService.insertProductType(productType) != 1){
            message = "添加成功！";

        }else {
            message = "添加失败！";
        }
        map.put("message", message);
        map.put("lastPage", getLastPage());
        return map;
    }

    @GetMapping("/delproducttype")
    @ResponseBody
    public Map<String, Object> deleteProductType(int id){
        Map<String, Object> map = new HashMap<>();
        String message = "";
        if(productTypeService.deleteProductTypeById(id) != 1){
            message = "删除成功！";

        }else {
            message = "删除失败！";
        }
        map.put("message", message);
        map.put("lastPage", getLastPage());
        return map;
    }

    @GetMapping("/producttypemodify")
    @ResponseBody
    public Map<String, Object> toUpdateProductTypePage(int id){
        Map<String, Object> map = new HashMap<>();
        ProductType productType = productTypeService.selectProductTypeById(id);
        map.put("productType", productType);
        return map;
    }

    @PostMapping("/updateprotype")
    @ResponseBody
    public Map<String, Object> updateProductType(ProductType productType){
        Map<String, Object> map = new HashMap<>();
        String message = "";
        if(productTypeService.updateProductTypeById(productType) != 1){
            message = "修改成功！";

        }else {
            message = "修改失败！";
        }
        map.put("message", message);
        return map;
    }

    private Integer getLastPage(){
        int count = productTypeService.getProductTypeRowCount(new ProductType());
        int lastPage = 1;
        if (count % 5 == 0) {
            lastPage = count / 5;
        }else {
            lastPage = count / 5 + 1;
        }
        return lastPage;
    }

}
