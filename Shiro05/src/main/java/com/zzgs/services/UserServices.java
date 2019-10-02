package com.zzgs.services;

import com.zzgs.domain.User;

import java.util.Set;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:32
 * Description:
 */

public interface UserServices {

    public User findByUserName(String username);

    /**
     * 根据角色名查询所有的角色信息
     */
    Set<String> findRoleByUserName(String username);
    //查询所有角色
    Set<String> findRoles();
}
