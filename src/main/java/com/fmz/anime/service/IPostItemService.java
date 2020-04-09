package com.fmz.anime.service;

import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.PageBean;
import com.fmz.anime.entity.Post;

public interface IPostItemService {

    PageBean<Post> pageQuery(int cid, int currentPage, int pageSize);
    boolean savePost(Post post);
}
