package com.xm.service;

import java.util.HashMap;
import java.util.List;

/**
 * @author lz
 * @date 2020/9/15 - 15:55
 * @function
 */
public interface ProductService {

    //获取所有的商品信息
    List<HashMap<String,Object>> getProducts();


}
