package com.fmz.anime.dao;

import com.fmz.anime.entity.Community;
import com.fmz.anime.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;

public class CommunityDaoImpl implements ICommunityDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int fid) {
        String sql = "select count(*) from community where fid = ?";
        return template.queryForObject(sql, Integer.class, fid);
    }

    /**
     * 查询当前页的数据集合，limit语句
     * @param fid
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Community> findByPage(int fid, int start, int pageSize) {
        String sql = "select * from community where fid = ? limit ?, ?";
        return template.query(sql,
                new BeanPropertyRowMapper<Community>(Community.class),fid,start,pageSize);
    }

    @Override
    public List<Community> findOne(int cid) {
        String sql = "select * from community where cid = ?";
        return template.query(sql, new BeanPropertyRowMapper<Community>(Community.class), cid);
    }

    @Override
    public void addPostNum(int cid) {
        String sql_1 = "select post_num from community where cid = ?";
        String sql_2 = "update community set post_num = ? where cid = ?";
        Integer origin = template.queryForObject(sql_1, Integer.class,cid);
        origin++;
        template.update(sql_2, origin, cid);
    }
}
