package com.fmz.anime.entity;

import java.io.Serializable;

/**
 * 板块实体类
 */
public class Field {

    private int fid;//分类id

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    private String fname;//分类名称


}
