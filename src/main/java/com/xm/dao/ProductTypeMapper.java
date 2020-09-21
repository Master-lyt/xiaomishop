package com.xm.dao;

import com.xm.entity.ProductType;
import com.xm.form.ProductTypeForm;
import com.xm.untils.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lz
 * @date 2020/9/16 - 16:05
 * @function
 */
public interface ProductTypeMapper {

    int getProductTypeRowCount(ProductType productType);
    List<ProductType> selectProductType();
    ProductType selectProductTypeById(Integer id);
    int insertProductType(@Param("value")ProductType value);
    int insertNonEmptyProductType(@Param("value")ProductType value);
    int deleteProductTypeById(Integer id);
    int updateProductTypeById(@Param("enti")ProductType enti);
    int updateNonEmptyProductTypeById(@Param("enti")ProductType enti);
    List<ProductType> selectProductTypeByPage(@Param("productType") ProductType productType, @Param("query")Query query);

}
