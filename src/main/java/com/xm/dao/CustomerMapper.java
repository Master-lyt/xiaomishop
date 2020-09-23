package com.xm.dao;

import com.xm.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    Customer getCustomerByCname(@Param("cname") String cname);

    Customer login(@Param("cname") String cname,@Param("cpass") String cpass);

    int registerCustomer( Customer customer);
}

