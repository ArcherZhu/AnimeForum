package com.fmz.anime.service;

import com.fmz.anime.dao.CommunityDaoImpl;
import com.fmz.anime.dao.ICommunityDao;
import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.PageBean;

import java.util.List;

public class CommunityServiceImpl implements ICommunityService {
    private ICommunityDao communityDao= new CommunityDaoImpl();
    @Override
    public PageBean<Community> pageQuery(int cid, int currentPage, int pageSize) {
        //这里封装pagebean返回
        PageBean<Community> pb = new PageBean<Community>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        //查询总记录数
        int totalCount = communityDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);
        //当前页显示数据集合
        int start = (currentPage-1)*pageSize;
        List<Community> list = communityDao.findByPage(cid,start,pageSize);
        pb.setList(list);
        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize:
                (totalCount/pageSize + 1);
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public List<Community> findOne(int cid) {
        return communityDao.findOne(cid);
    }

    @Override
    public void addPostNum(int cid) {
        communityDao.addPostNum(cid);
    }
    


}
