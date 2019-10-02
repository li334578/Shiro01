package com.zzgs.services.Impl;

import com.zzgs.dao.UsersDao;
import com.zzgs.domain.User;
import com.zzgs.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:33
 * Description:
 */
@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UsersDao usersDao;


    @Override
    public User findByUserName(String username) {

        return usersDao.findByUserName(username);
    }
}
