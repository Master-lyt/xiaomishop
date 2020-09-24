package com.xm.controller;

import com.xm.entity.PageBean;
import com.xm.entity.Product;
import com.xm.entity.ProductType;
import com.xm.service.ProductService;
import com.xm.service.ProductTypeService;
import com.xm.untils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String showProduct(Model model){
        List<ProductType> productTypes = productTypeService.selectProductType();
        model.addAttribute("ptlist", productTypes);
        return "productbypage";//WEB-INF/jsp/productnopage.jsp
    }

    //删除一行商品
    @GetMapping("/delproduct")
    @ResponseBody
    public Map<String, String> delProductById(int id){
        int i = productService.delProductById(id);
        Map<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }

    //进入添加商品页面
    @GetMapping("/addproductpage")
    public String toAddProductPage(Model model){
        //需要提前查询商品的类型数据，进入页面后可以直接选择商品类型
        //调用商品类型的业务
        List<ProductType> productTypes = productTypeService.selectProductType();
        model.addAttribute("ptlist", productTypes);
        return "addproduct";
    }

    //实现批量删除
    /*
    *1.需要获取参数一个或多个id 放到数组int【】 ids中
    *2.
    * *  */
    @GetMapping("/batchdelproduct_ajax")
    @ResponseBody
    public Map<String, Object> delBatchProduct(int[] ids){
        System.out.println("delBatchProduct---->" + Arrays.toString(ids));
        productService.delBatchProduct(ids);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "success");
        return resultMap;
    }

    //实现新增商品
    @PostMapping("/addproduct")
    @ResponseBody
    public Map<String, String> addProduct(Product product) {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        Timestamp ts = Timestamp.valueOf(dateStr);
        //调用业务层
        product.setDate(ts);
        productService.addProduct(product);
        Map<String, String> map = new HashMap<>();
        map.put("status", "success");
        //返回新增商品页面
        return map;
    }

    //上传图片
    @PostMapping("/produpload")
    @ResponseBody
    public Map<String, String> produpload(HttpServletRequest request, MultipartFile upimage) throws IOException {
        Map<String, String> map = new HashMap<>();
        String path = request.getSession().getServletContext().getRealPath("/resources/image_big/");
        String str = "/resources/image_big/";
        System.out.println(path);
        String fileName = upimage.getOriginalFilename();
        File dir = new File(path, fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        upimage.transferTo(dir);
        str += fileName;
        System.out.println(str);
        map.put("imgurl", str);
        map.put("imgName", fileName);
        return map;
    }
    @GetMapping("/getproductbyid")
    public String getProductById(int id, Model model){
        Product product = productService.getProductById(id);
        List<ProductType> plist = productTypeService.selectProductType();
        System.out.println("p:" + product);
        model.addAttribute("product", product);
        model.addAttribute("ptlist", plist);
        return "updateproduct";
    }

    @PostMapping("/updateproduct")
    public String updateProduct(Product product){
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        Timestamp ts = Timestamp.valueOf(dateStr);
        product.setDate(ts);
        System.out.println("/updateproduct---->product's image" + product.getImage());
        productService.updateProduct(product);
        return "redirect:/getproductbypage";
    }

    @PostMapping("/product_update_ajax")
    @ResponseBody
    public Map<String, String> updateProductAjax(Product product){
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        Timestamp ts = Timestamp.valueOf(dateStr);
        product.setDate(ts);
        System.out.println("/updateProductAjax---->product" + product.toString());
        productService.updateProduct(product);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("status","success");
        return resultMap;
    }

    //获取商品详情
    @GetMapping("/getproductdetail")
    public String getProductDetail(int id,Model model){

        Product product=productService.getProductById(id);
        model.addAttribute("product",product);

        return "productdetail";
    }

    @GetMapping("product_list_ajax")
    @ResponseBody
    public Map<String, Object> getProductListAjax(@RequestParam(name = "Name", defaultValue = "") String Name,
                                                  @RequestParam(name = "pn", defaultValue = "-1") int pn,
                                                  @RequestParam(name = "typeId", defaultValue = "-1") int typeId){
        Query query = new Query(pn, 5);
        PageBean<Product> products = productService.getProductListAjax(Name, typeId, query);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", products.getList());
        resultMap.put("pageSize", query.getPs());
        resultMap.put("pageCount", products.getPages());
        resultMap.put("rowCount", products.getRowcount());
        return resultMap;
    }

    @GetMapping("getproductbyid_ajax")
    @ResponseBody
    public Map<String, Object> getProductByIdAjax(int id){
        Map<String, Object> resultMap = new HashMap<>();
        Product product = productService.getProductById(id);
        List<ProductType> plist = productTypeService.selectProductType();
        resultMap.put("product", product);
        resultMap.put("plist", plist);
        return resultMap;
    }
}
