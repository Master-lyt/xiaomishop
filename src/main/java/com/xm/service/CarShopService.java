package com.xm.service;

import org.apache.ibatis.annotations.Param;

public interface CarShopService {
    int addCarshop(@Param("customerid") int customerid,@Param("pid") int pid,@Param("numbers") String numbers);
}
