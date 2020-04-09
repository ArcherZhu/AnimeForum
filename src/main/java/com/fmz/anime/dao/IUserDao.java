package com.fmz.anime.dao;

import com.fmz.anime.entity.User;

public interface IUserDao {

        User findByUsername(String username);

        void save(User user);

        User findByUsernameAndPassword(String username, String password);

        User findUserById(int uid);
}
