package com.fmz.anime.service;

import com.fmz.anime.dao.IPostItemDao;
import com.fmz.anime.dao.PostItemDaoImpl;
import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.PageBean;
import com.fmz.anime.entity.Post;

import java.util.List;

public class PostItemServiceImpl implements IPostItemService{
    private IPostItemDao postItemDao = new PostItemDaoImpl();
    @Override
    public PageBean<Post> pageQuery(int cid, int currentPage, int pageSize) {
        //这里封装pagebean返回
        PageBean<Post> pb = new PageBean<Post>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        //查询总记录数
        int totalCount = postItemDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);
        //当前页显示数据集合
        int start = (currentPage-1)*pageSize;
        List<Post> list = postItemDao.findByPage(cid,start,pageSize);
        pb.setList(list);
        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize:
                (totalCount/pageSize + 1);
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public boolean savePost(Post post) {
        postItemDao.savePost(post);
        return true;
    }
}
