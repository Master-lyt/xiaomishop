package com.xm.dao;

import com.xm.entity.ProductType;

import java.util.List;

/**
 * @author lz
 * @date 2020/9/16 - 16:05
 * @function
 */
public interface ProductTypeMapper {

    long getProductTypeRowCount();
    List<ProductType> selectProductType();
    ProductType selectProductTypeById(Integer id);
    int insertProductType(ProductType value);
    int insertNonEmptyProductType(ProductType value);
    int deleteProductTypeById(Integer id);
    int updateProductTypeById(ProductType enti);
    int updateNonEmptyProductTypeById(ProductType enti);

}
