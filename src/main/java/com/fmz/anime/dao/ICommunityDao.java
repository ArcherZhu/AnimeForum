package com.fmz.anime.dao;

import com.fmz.anime.entity.Community;

import java.util.List;

public interface ICommunityDao {
    int findTotalCount(int fid);
    List<Community> findByPage(int fid, int start, int pageSize);
    List<Community> findOne(int cid);
    //增加帖子数
    void addPostNum(int cid);
}
