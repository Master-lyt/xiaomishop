package com.xm.dao;

import com.xm.entity.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lz
 * @date 2020/9/16 - 16:05
 * @function
 */
public interface ProductTypeMapper {

    int getProductTypeRowCount();
    List<ProductType> selectProductType();
    ProductType selectProductTypeById(Integer id);
    int insertProductType(ProductType value);
    int insertNonEmptyProductType(ProductType value);
    int deleteProductTypeById(Integer id);
    int updateProductTypeById(ProductType enti);
    int updateNonEmptyProductTypeById(ProductType enti);
    List<ProductType> selectProductTypeByPage(@Param("page") int page, @Param("typename") String typename, @Param("pagesize") int pagesize);

}
