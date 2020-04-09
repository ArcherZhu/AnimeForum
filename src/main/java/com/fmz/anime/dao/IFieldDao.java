package com.fmz.anime.dao;

import com.fmz.anime.entity.Field;

import java.util.List;

public interface IFieldDao {

    List<Field> findAll();
    List<Field> findOne(int fid);
}
