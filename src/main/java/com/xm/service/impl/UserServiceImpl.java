package com.xm.service.impl;

import com.xm.dao.RoleMapper;
import com.xm.dao.UserMapper;
import com.xm.entity.PageBean;
import com.xm.entity.ProductType;
import com.xm.entity.Role;
import com.xm.entity.User;
import com.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public PageBean<HashMap<String, Object>> getAllUsersByPage(String name, int typeId, int page, int pagesize) {
        List<HashMap<String, Object>> list = userMapper.getAllUsersByPage(name, typeId, page, pagesize);

        PageBean<HashMap<String, Object>> pb = new PageBean<>();
        pb.setPage(page);
        pb.setList(list);
        int rowcount = rowcount(name, typeId);
        if (rowcount % pagesize == 0) {
            pb.setPages(rowcount / pagesize);
        }else {
            pb.setPages(rowcount / pagesize + 1);
        }
        return pb;
    }


    @Override
    public int delUser(int id) {
        return userMapper.delUser(id);
    }

    @Override
    public int batchDelUsers(int[] ids) {
        return userMapper.batchDelUser(ids);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    private int rowcount(String name, int typeId){
        return userMapper.getRowcount(name, typeId);
    }
}
