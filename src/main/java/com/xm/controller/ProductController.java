package com.xm.controller;

import com.xm.entity.PageBean;
import com.xm.entity.ProductType;
import com.xm.service.ProductService;
import com.xm.service.ProductTypeService;
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

    @Autowired
    private ProductTypeService productTypeService;


    //获取所有的商品的信息
    @GetMapping("/getproductbypage")
    public String showProduct(@RequestParam(name = "name", defaultValue = "") String name,
                              @RequestParam(name = "typeid", defaultValue = "-1") int typeId,
                              @RequestParam(name = "page", defaultValue = "1") int page, Model model){
        int pagesize = 5;
        PageBean<HashMap<String, Object>> products = productService.getAllProductByPage(name, typeId, page, pagesize);
        model.addAttribute("pagebean", products);
        model.addAttribute("ptlist",productTypeService.selectProductType());
        model.addAttribute("name", name);
        model.addAttribute("typeid", typeId);
        //去页面product.jsp
        return "productbypage";//WEB-INF/jsp/productnopage.jsp
    }

    //删除一行商品
    @GetMapping("/delproduct")
    public String delProductById(int id){
        productService.delProductById(id);
        return "redirect:/getproductbypage";
    }

    //进入添加商品页面
    @GetMapping("addproductpage")
    public String toAddProductPage(Model model){
        //需要提前查询商品的类型数据，进入页面后可以直接选择商品类型
        //调用商品类型的业务
        List<ProductType> productTypes = productTypeService.selectProductType();
        model.addAttribute("ptlist",productTypes);
        return "addProduct";
    }

    //实现批量删除
    /*
    *1.需要获取参数一个或多个id 放到数组int【】 ids中
    *2.
    * *  */
    @GetMapping("batchdelproduct")
    public String delBatchProduct(int[] ids){
        //调用业务层
        //返回到商品信息页面

        //测试信息
        /*for(int id : ids){
            System.out.println("========="+id);
        }*/

        productService.delBatchProduct(ids);
        return "redirect:/getproductbypage";
    }


}
