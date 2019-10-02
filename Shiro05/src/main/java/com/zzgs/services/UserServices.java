package com.zzgs.services;

import com.zzgs.domain.User;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:32
 * Description:
 */

public interface UserServices {

    public User findByUserName(String username);
}
