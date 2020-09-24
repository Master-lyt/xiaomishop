package com.xm.service;

import com.xm.entity.CarShop;

import java.util.HashMap;
import java.util.List;

public interface CarShopService {

    int addCarshop(CarShop carShop);

    CarShop getCarShopByCustomerId(CarShop carShop);

    int updateCarShopAdd(CarShop carShop);

    int updateCarShopById(int cid,int num);

    List<HashMap<String, Object>> getShopCar(int customerid);

    int delCarShop(int cid, int customerid);

}
