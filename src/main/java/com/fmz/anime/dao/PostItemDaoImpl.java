package com.fmz.anime.dao;

import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.Post;
import com.fmz.anime.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PostItemDaoImpl implements IPostItemDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid) {
        String sql = "select count(*) from post_item where community_id = ?";
        return template.queryForObject(sql, Integer.class, cid);
    }

    @Override
    public List<Post> findByPage(int cid, int start, int pageSize) {
        String sql = "select * from post_item where community_id = ? limit ?, ?";
        return template.query(sql,
                new BeanPropertyRowMapper<Post>(Post.class),cid,start,pageSize);
    }

    @Override
    public void savePost(Post post) {
        String sql = "insert into post_item(community_id,title,contents,user_id,pick_num, up_time) values(?,?,?,?,?,?)";
        template.update(sql, post.getCommunity_id(), post.getTitle(), post.getContents(), post.getUser_id(),
                post.getPick_num(),post.getUp_time());
    }
}
