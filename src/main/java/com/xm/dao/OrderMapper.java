package com.xm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {
    List<HashMap<String, Object>> getAllOrder(@Param("page") int page, @Param("pagesize") int pagesize);

    int getRowcount(@Param("name") String name);
}
