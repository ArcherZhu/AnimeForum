package com.fmz.anime.service;

import com.fmz.anime.dao.FieldDaoImpl;
import com.fmz.anime.dao.IFieldDao;
import com.fmz.anime.entity.Field;

import java.util.List;

public class FieldServiceImpl implements IFieldService {
    private IFieldDao fieldDao = new FieldDaoImpl();

    @Override
    public List<Field> findAll() {
        return fieldDao.findAll();

    }

    @Override
    public List<Field> findOne(int fid) {
        return fieldDao.findOne(fid);
    }

}
