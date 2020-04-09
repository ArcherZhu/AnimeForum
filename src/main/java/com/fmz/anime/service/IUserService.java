package com.fmz.anime.service;

import com.fmz.anime.entity.*;

public interface IUserService {
    boolean register(User user);
    //登录返回一个User
    User login(User user);
    User findUserById(int uid);
}
