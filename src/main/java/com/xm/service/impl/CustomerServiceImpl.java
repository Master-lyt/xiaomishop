package com.xm.service.impl;

import com.xm.dao.CustomerMapper;
import com.xm.entity.Customer;
import com.xm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer getCustomerByCname(String cname) {
        return customerMapper.getCustomerByCname(cname);
    }

    @Override
    public Customer login(String cname,String cpass){
        return customerMapper.login(cname,cpass);
    }

    //用户注册传参到数据库
    @Override
    public int registerCustomer(Customer customer){
        return customerMapper.registerCustomer(customer);

    }
}
