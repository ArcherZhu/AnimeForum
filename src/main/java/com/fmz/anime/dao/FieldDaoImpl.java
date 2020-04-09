package com.fmz.anime.dao;

import com.fmz.anime.entity.Field;
import com.fmz.anime.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FieldDaoImpl implements IFieldDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Field> findAll() {
        String sql = "select * from field";
        return template.query(sql, new BeanPropertyRowMapper<Field>(Field.class));
    }

    @Override
    public List<Field> findOne(int fid) {
        String sql = "select * from field where fid = ?";
        return template.query(sql, new BeanPropertyRowMapper<Field>(Field.class), fid);
    }

}
