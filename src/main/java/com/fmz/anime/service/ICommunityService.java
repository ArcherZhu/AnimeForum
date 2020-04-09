package com.fmz.anime.service;

import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.PageBean;

import java.util.List;

public interface ICommunityService {
    PageBean<Community> pageQuery(int cid, int currentPage, int pageSize);
    List<Community> findOne(int cid);
    void addPostNum(int cid);

}
