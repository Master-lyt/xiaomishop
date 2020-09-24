package com.xm.dao;

import com.xm.entity.CarShop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CarShopMapper {
    int addCarshop(@Param("carShop") CarShop carShop);

    CarShop getCarShopByCustomerId(@Param("carShop") CarShop carShop);

    int updateCarShopAdd(@Param("carShop") CarShop carShop);

    int updateCarShopById(@Param("id")int cid, @Param("num")int num);

    List<HashMap<String, Object>> getShopCar(int customerid);

    int delCarShop(@Param("cid") int cid, @Param("customerid") int customerid);
}
