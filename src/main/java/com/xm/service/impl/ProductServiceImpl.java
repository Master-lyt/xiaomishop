package com.xm.service.impl;

import com.xm.dao.ProductMapper;
import com.xm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author lz
 * @date 2020/9/15 - 15:58
 * @function
 */
@Service
public class ProductServiceImpl implements ProductService {

    //注入数据访问层
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<HashMap<String, Object>> getProducts() {
        return productMapper.getProducts();
    }

}
