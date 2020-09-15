package com.xm.dao;

import com.xm.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @author lz
 * @date 2020/9/15 - 15:53
 * @function
 */
@Repository
public interface RoleMapper {

    //通过id查询角色的名称
    Role getRoleById(int id);

}
