package com.xm.service;

import com.xm.entity.PageBean;
import com.xm.entity.Role;
import com.xm.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author lz
 * @date 2020/9/15 - 15:55
 * @function
 */
public interface UserService {

    //登录方法
    HashMap<String,Object> userLogin(User user);

    PageBean<HashMap<String, Object>> getAllUsersByPage(String name, int typeId, int page, int pagesize);

    int delUser(int id);

    int batchDelUsers(int[] ids);

    User getUserById(int id);

    int updateUser(User user);

    int addUser(User user);
}
