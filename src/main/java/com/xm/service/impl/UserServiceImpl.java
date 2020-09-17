package com.xm.service.impl;

import com.xm.dao.RoleMapper;
import com.xm.dao.UserMapper;
import com.xm.entity.User;
import com.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author lz
 * @date 2020/9/15 - 15:56
 * @function
 */
@Service
public class UserServiceImpl implements UserService {

    //调用数据访问层
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    //用户登录
    @Override
    public HashMap<String,Object> userLogin(User user) {
//		Users  users = usersMapper.userLogin(user);
//		//获取到角色的id，使用角色的id查询角色的名称rolename
//		int roleid = users.getRoleid();
//		Role role = roleMapper.getRoleById(roleid);
//		users.setRole(role);
        return userMapper.getUser(user);
    }

}
