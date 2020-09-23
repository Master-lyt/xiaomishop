package com.xm.service.impl;


import com.xm.dao.CarShopMapper;
import com.xm.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarShopServiceImpl implements CarShopService {

    @Autowired
    private CarShopMapper carShopMapper;

    @Override
    public int addCarshop(int customerid,int pid,String numbers){
        return  carShopMapper.addCarshop(customerid, pid, numbers);

    }
}
