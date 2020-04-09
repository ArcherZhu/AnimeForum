package com.fmz.anime.service;

import com.fmz.anime.dao.IUserDao;
import com.fmz.anime.dao.UserDaoImpl;
import com.fmz.anime.entity.User;

public class UserServiceImpl implements IUserService {

    IUserDao dao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        User uu = dao.findByUsername(user.getUsername());
        //判断u是否为null
        if (uu != null) {
            //用户名存在，注册失败
            return false;
        }
        dao.save(user);
        return true;
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public User findUserById(int uid) {
        return dao.findUserById(uid);
    }
}
