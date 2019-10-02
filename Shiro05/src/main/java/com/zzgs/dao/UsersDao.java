package com.zzgs.dao;

import com.zzgs.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:32
 * Description:
 */

public interface UsersDao {

    @Select("select * from user where username = #{username}")
    public User findByUserName(String username);
}
