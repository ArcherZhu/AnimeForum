package com.fmz.anime.dao;

import com.fmz.anime.entity.User;
import com.fmz.anime.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements IUserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        String sql = "select * from user where username = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
        }

        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into user(username,password,nickname,birthday,sex, email) values(?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getNickname(),
                user.getBirthday(), user.getSex(), user.getEmail());
    }


    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username=? and password=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    username, password);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public User findUserById(int uid) {
        String sql = "select * from user where uid=?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
        } catch (Exception e) {
        }
        return user;

    }

}
