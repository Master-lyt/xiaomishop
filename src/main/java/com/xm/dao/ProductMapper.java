package com.xm.dao;

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

    List<HashMap<String, Object>> getAllProductByPage(@Param("page") int page, @Param("pagesize") int pagesize);

    int getRowcount();

    int delProductById(@Param("id") int id);

    int delBatchProduct(int[] ids);
}
