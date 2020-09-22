package com.xm.dao;

import com.xm.entity.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    Customer getCustomerByCname(@Param("cname") String cname);

    Customer login( @Param("cname") String cname,@Param("cpass") String cpass);

    int registerCustomer( Customer customer);
}

