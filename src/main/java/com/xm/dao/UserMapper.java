package com.xm.dao;

import com.xm.entity.Role;
import com.xm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author lz
 * @date 2020/9/15 - 15:33
 * @function
 */
@Repository
public interface UserMapper {

    HashMap<String, Object> getUser(User user);

    List<HashMap<String, Object>> getAllUsersByPage(@Param("name") String name,
                                                    @Param("typeId") int typeId,
                                                    @Param("page") int page,
                                                    @Param("pagesize") int pagesize);

    int getRowcount(@Param("name") String name, @Param("typeId") int typeId);

    int delUser(int id);

    int batchDelUser(int[] ids);

    User getUserById(int id);

    int updateUser(User user);

    int addUser(User user);
}
