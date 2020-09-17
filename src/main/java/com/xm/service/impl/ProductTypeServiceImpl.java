package com.xm.service.impl;

import com.xm.dao.ProductTypeMapper;
import com.xm.entity.ProductType;
import com.xm.service.ProductTypeService;
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
    public long getProductTypeRowCount(){
        return productTypeMapper.getProductTypeRowCount();
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
        return productTypeMapper.updateProductTypeById(enti);
    }

    @Override
    public int updateNonEmptyProductTypeById(ProductType enti){
        return productTypeMapper.updateNonEmptyProductTypeById(enti);
    }

    public ProductTypeMapper getProductTypeMapper() {
        return this.productTypeMapper;
    }

    public void setProductTypeMapper(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }


}
