package com.fmz.anime.entity;

/**
 * 帖子实体类
 */
public class Post {
    int pid;
    int community_id;
    int user_id;
    String title;
    int pick_num;
    String up_time;

    public int getPick_num() {
        return pick_num;
    }

    public void setPick_num(int pick_num) {
        this.pick_num = pick_num;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    String contents;
}
