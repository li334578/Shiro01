package com.zzgs.dao;

import com.zzgs.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:32
 * Description:
 */

public interface UsersDao {

    @Select("select * from user where username = #{username}")
    public User findByUserName(String username);

    //根据用户名查询所有的用户信息

    @Select("SELECT role.rname FROM role WHERE role.id =(" +
            "SELECT user_role.rid FROM user_role WHERE user_role.uid =(" +
            "SELECT user.id FROM USER WHERE user.username = #{username}))")
    Set<String> findRoleByUserName(String username);

    @Select("select rname from role where status = 1")
    Set<String> findRoles();
}
