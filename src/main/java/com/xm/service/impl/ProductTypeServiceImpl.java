package com.xm.service.impl;

import com.xm.dao.ProductTypeMapper;
import com.xm.entity.PageBean;
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
    public int getProductTypeRowCount(){
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

    @Override
    public PageBean<ProductType> selectProductTypeByPage(int page, String typename, int pagesize) {
        List<ProductType> list = productTypeMapper.selectProductTypeByPage(page, typename, pagesize);

        PageBean<ProductType> pb = new PageBean<>();
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
        return productTypeMapper.getProductTypeRowCount();
    }

    public ProductTypeMapper getProductTypeMapper() {
        return this.productTypeMapper;
    }

    public void setProductTypeMapper(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }


}
