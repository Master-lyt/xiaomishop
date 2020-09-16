package com.xm.service.impl;

import com.xm.dao.ProductMapper;
import com.xm.service.ProductService;
import com.xm.untils.PageBean;
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
    public List<HashMap<String, Object>> getALLProducts() {
        return productMapper.getALLProducts();
    }

    @Override
    public PageBean<HashMap<String, Object>> getAllProductByPage(int page, int pagesize) {
        List<HashMap<String, Object>> list = productMapper.getAllProductByPage(page, pagesize);

        PageBean<HashMap<String, Object>> pb = new PageBean<>();
        pb.setPage(page);
        pb.setList(list);
        int rowcount = rowcount();
        if (rowcount % pagesize == 0) {
            pb.setPages(rowcount / pagesize);
        }else {
            pb.setPages(rowcount / pagesize + 1);
        }
        return pb;
    }

    private int rowcount(){
        return productMapper.getRowcount();
    }
}
