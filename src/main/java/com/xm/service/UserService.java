package com.xm.service;

import com.xm.entity.User;

import java.util.HashMap;

/**
 * @author lz
 * @date 2020/9/15 - 15:55
 * @function
 */
public interface UserService {

    //登录方法
    HashMap<String,Object> userLogin(User user);

}
