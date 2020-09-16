package com.xm.dao;

import com.xm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author lz
 * @date 2020/9/15 - 15:33
 * @function
 */
@Repository
public interface UserMapper {

    HashMap<String, Object> getUser(User user);

}
