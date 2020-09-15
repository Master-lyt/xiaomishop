package com.xm.dao;

import com.xm.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lz
 * @date 2020/9/14 - 15:26
 * @function
 */
@Repository
public interface AddressMapper {

    public List<Address> getAllAddress();

}
