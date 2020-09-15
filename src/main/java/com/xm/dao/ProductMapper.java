package com.xm.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author lz
 * @date 2020/9/15 - 15:54
 * @function
 */
@Repository
public interface ProductMapper {

    //获取所有的商品信息
    List<HashMap<String,Object>> getProducts();

}
