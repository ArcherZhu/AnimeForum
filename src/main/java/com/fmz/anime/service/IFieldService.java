package com.fmz.anime.service;

import com.fmz.anime.entity.Field;

import java.util.List;

public interface IFieldService {
    List<Field> findAll();
    List<Field> findOne(int cid);
}
