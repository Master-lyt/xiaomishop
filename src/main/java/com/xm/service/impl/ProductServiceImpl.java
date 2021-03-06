package com.xm.service.impl;


import com.xm.entity.PageBean;
import com.xm.entity.Product;
import com.xm.dao.ProductMapper;
import com.xm.service.ProductService;
import com.xm.untils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

//    @Override
//    public PageBean<HashMap<String, Object>> getAllProductByPage(String name, int typeId, int page, int pagesize) {
//        List<HashMap<String, Object>> list = productMapper.getAllProductByPage(name, typeId, page, pagesize);
//
//        PageBean<HashMap<String, Object>> pb = new PageBean<>();
//        pb.setPage(page);
//        pb.setList(list);
//        int rowcount = rowcount(name, typeId);
//        if (rowcount % pagesize == 0) {
//            pb.setPages(rowcount / pagesize);
//        }else {
//            pb.setPages(rowcount / pagesize + 1);
//        }
//        return pb;
//    }

    private int rowcount(String name, int typeId){
        return productMapper.getRowcount(name, typeId);
    }

    @Override
    public int delProductById(int id){
        return productMapper.delProductById(id);

    }

    //批量删除
    @Override
    public int delBatchProduct(int[] ids){
        return productMapper.delBatchProduct(ids);
    }

    //增加商品
    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return productMapper.getProductById(id);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public List<Product> getProductFiveList(){ return productMapper.getProductFiveList();}

    @Override
    public PageBean<Product> getProductListAjax(String Name, int typeid, Query query) {
        List<Product> list = productMapper.getAllProductByPage(Name, typeid, query.getPn(), query.getPs());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Product p : list) {
            p.setSDate(sdf.format(p.getDate()));
        }
        PageBean<Product> pb = new PageBean<>();
        pb.setPage(query.getPn());
        pb.setList(list);
        int rowcount = rowcount(Name, typeid);
        if (rowcount % query.getPs() == 0) {
            pb.setPages(rowcount / query.getPs());
        }else {
            pb.setPages(rowcount / query.getPs() + 1);
        }
        return pb;
    }
}
