package com.fmz.anime.dao;

import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.Post;

import java.util.List;

public interface IPostItemDao {
    int findTotalCount(int cid);
    List<Post> findByPage(int cid, int start, int pageSize);
    void savePost(Post post);
}
