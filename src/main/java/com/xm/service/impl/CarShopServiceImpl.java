package com.xm.service.impl;


import com.xm.dao.CarShopMapper;
import com.xm.entity.CarShop;
import com.xm.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CarShopServiceImpl implements CarShopService {

    @Autowired
    private CarShopMapper carShopMapper;

    @Override
    public int addCarshop(CarShop carShop) {
        return carShopMapper.addCarshop(carShop);
    }

    @Override
    public CarShop getCarShopByCustomerId(CarShop carShop) {
        return carShopMapper.getCarShopByCustomerId(carShop);
    }

    @Override
    public int updateCarShopAdd(CarShop carShop) {
        return carShopMapper.updateCarShopAdd(carShop);
    }

    @Override
    public int updateCarShopById(int cid, int num) {
        return carShopMapper.updateCarShopById(cid, num);
    }

    @Override
    public List<HashMap<String, Object>> getShopCar(int customerid) {
        return carShopMapper.getShopCar(customerid);
    }

    @Override
    public int delCarShop(int cid, int customerid) {
        return carShopMapper.delCarShop(cid, customerid);
    }


}
