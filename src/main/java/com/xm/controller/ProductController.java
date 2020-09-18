package com.xm.controller;

import com.xm.entity.PageBean;
import com.xm.entity.Product;
import com.xm.entity.ProductType;
import com.xm.service.ProductService;
import com.xm.service.ProductTypeService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/getprobypage")
    public String showProduct(@RequestParam(name = "page", defaultValue = "1") int page, Model model){
        int pagesize = 5;
        PageBean<HashMap<String, Object>> products = productService.getAllProductByPage(page, pagesize);
        model.addAttribute("pagebean", products);
        //去页面product.jsp
        List<ProductType> productTypes = productTypeService.selectProductType();
        model.addAttribute("ptlist",productTypes);

        return "productbypage";//WEB-INF/jsp/productnopage.jsp
    }

    //删除一行商品
    @GetMapping("/delproduct")
    public String delProductById(int id){
        System.out.println("id:" + id);
        int i = productService.delProductById(id);
        System.out.println("i:" + i);
        return "redirect:/getprobypage";
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
    @GetMapping("/batchdelproduct")
    public String delBatchProduct(int[] ids){
        //调用业务层
        //返回到商品信息页面

        //测试信息
//        for(int id : ids){
//            System.out.println("========="+id);
//        }

        productService.delBatchProduct(ids);
        return "redirect:/getprobypage";
    }


    //实现新增商品
    @PostMapping("/addproduct")
    public String addProduct(Product product) {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        Timestamp ts = Timestamp.valueOf(dateStr);
        //调用业务层
        product.setDate(ts);
        productService.addProduct(product);
        //返回新增商品页面
        return "redirect://addproductpage";
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
        System.out.println(product.toString());
        productService.updateProduct(product);
        return "redirect://getprobypage";
    }
}
