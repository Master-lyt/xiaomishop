package com.xm.dao;

import  com.xm.entity.Product;
import org.apache.ibatis.annotations.Param;
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
    List<HashMap<String,Object>> getALLProducts();

    List<Product> getAllProductByPage(@Param("name") String name, @Param("typeId") int typeId, @Param("page") int page, @Param("pagesize") int pagesize);

    int getRowcount(@Param("name") String name, @Param("typeId") int typeId);

    int delProductById(@Param("id") int id);

    int delBatchProduct(int[] ids);

    int addProduct(Product product);

    int updateProduct(Product product);

    Product getProductById(@Param("id") int id);

    List<Product> getProductFiveList();
}
