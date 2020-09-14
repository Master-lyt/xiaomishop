package com.xm.service.impl;

import com.xm.dao.AddressMapper;
import com.xm.entity.Address;
import com.xm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lz
 * @date 2020/9/14 - 16:30
 * @function
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> getAllAddress() {
        return addressMapper.getAllAddress();
    }

}
