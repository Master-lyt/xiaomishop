package com.xm.service;

import com.xm.entity.PageBean;

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
    int delProductById( int id);

    //分页获取所有商品
    PageBean<HashMap<String, Object>> getAllProductByPage(int page, int pagesize);
}
