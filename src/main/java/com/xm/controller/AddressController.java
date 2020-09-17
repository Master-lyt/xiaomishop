package com.xm.controller;

import com.xm.entity.Address;
import com.xm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author lz
 * @date 2020/9/14 - 16:32
 * @function
 */
@Controller
@RequestMapping("/xad")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/getaddress")
    public String getAllAddress(Map<String, Object> map){
        List<Address> address = addressService.getAllAddress();

        map.put("address", address);
        return "list";
    }

}
