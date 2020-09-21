package com.xm.service.impl;

import com.xm.dao.ProductTypeMapper;
import com.xm.entity.PageBean;
import com.xm.entity.ProductType;
import com.xm.form.ProductTypeForm;
import com.xm.service.ProductTypeService;
import com.xm.untils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lz
 * @date 2020/9/16 - 16:11
 * @function
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public int getProductTypeRowCount(ProductType productType){
        return productTypeMapper.getProductTypeRowCount(productType);
    }

    @Override
    public List<ProductType> selectProductType(){
        return productTypeMapper.selectProductType();
    }

    @Override
    public ProductType selectProductTypeById(Integer id){
        return productTypeMapper.selectProductTypeById(id);
    }

    @Override
    public int insertProductType(ProductType value){
        return productTypeMapper.insertProductType(value);
    }

    @Override
    public int insertNonEmptyProductType(ProductType value){
        return productTypeMapper.insertNonEmptyProductType(value);
    }

    @Override
    public int deleteProductTypeById(Integer id){
        return productTypeMapper.deleteProductTypeById(id);
    }

    @Override
    public int updateProductTypeById(ProductType enti){
        System.out.println(enti);
        return productTypeMapper.updateProductTypeById(enti);
    }

    @Override
    public int updateNonEmptyProductTypeById(ProductType enti){
        return productTypeMapper.updateNonEmptyProductTypeById(enti);
    }

    @Override
    public PageBean<ProductType> selectProductTypeByPage(ProductType productType, Query query) {
        List<ProductType> list = productTypeMapper.selectProductTypeByPage(productType, query);

        PageBean<ProductType> pb = new PageBean<>();
        pb.setPage(query.getPn());
        pb.setList(list);
        int rowcount = rowcount(productType);
        Integer ps = query.getPs();
        if (rowcount % ps == 0) {
            pb.setPages(rowcount / ps);
        }else {
            pb.setPages(rowcount / ps + 1);
        }
        return pb;
    }

    private int rowcount(ProductType productType){
        return productTypeMapper.getProductTypeRowCount(productType);
    }

    public ProductTypeMapper getProductTypeMapper() {
        return this.productTypeMapper;
    }

    public void setProductTypeMapper(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }


}
