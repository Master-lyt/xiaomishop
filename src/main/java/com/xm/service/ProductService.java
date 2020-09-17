package com.xm.service;

import com.xm.entity.PageBean;
import com.xm.entity.Product;

import java.util.HashMap;
import java.util.List;

/**
 * @author lz
 * @date 2020/9/15 - 15:55
 * @function
 */
public interface ProductService {

    //获取所有的商品信息
    List<HashMap<String,Object>> getALLProducts();

    //删除商品
    int delProductById(int id);

    //分页获取所有商品
    PageBean<HashMap<String, Object>> getAllProductByPage(String name, int typeId, int page, int pagesize);

    //批量删除商品
    int delBatchProduct(int[] ids);

    //新增商品
    int addProduct(Product product);
}
