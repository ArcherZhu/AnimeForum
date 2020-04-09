package com.fmz.anime.entity;

import java.util.List;

/**
 * 社区实体类
 */
public class Community {

    private int cid;
    private String cname;

    private int post_num;
    private String commuIntroduce;


    private String cimage;

    private int fid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getPost_num() {
        return post_num;
    }

    public void setPost_num(int post_num) {
        this.post_num = post_num;
    }

    public String getCommuIntroduce() {
        return commuIntroduce;
    }

    public void setCommuIntroduce(String commuIntroduce) {
        this.commuIntroduce = commuIntroduce;
    }

    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }


}
