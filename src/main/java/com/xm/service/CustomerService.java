package com.xm.service;

import com.xm.entity.Customer;

import java.util.HashMap;

public interface CustomerService {

     //获取用户的账户密码等
     Customer getCustomerByCname(String cname);
     Customer login(String cname,String cpass);
     int registerCustomer(Customer customer);

}
