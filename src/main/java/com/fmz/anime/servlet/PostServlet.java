package com.fmz.anime.servlet;

import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.PageBean;
import com.fmz.anime.entity.Post;
import com.fmz.anime.entity.User;
import com.fmz.anime.service.CommunityServiceImpl;
import com.fmz.anime.service.ICommunityService;
import com.fmz.anime.service.IPostItemService;
import com.fmz.anime.service.PostItemServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/post/*")
public class PostServlet extends BaseServlet {
    private IPostItemService postService = new PostItemServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        PageBean<Post> routePageBean = postService.pageQuery(cid, currentPage, pageSize);
        writeValue(response, routePageBean);
    }

    public void makePost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Post post = new Post();
        post.setTitle(map.get("post_title")[0]);
        post.setContents(map.get("post_content")[0]);
        post.setCommunity_id(Integer.valueOf(map.get("pcid")[0]));
        //设置初始点赞数0
        post.setPick_num(0);

        //设置更新时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        post.setUp_time(df.format(new Date()));

        //设置user_id
        User user = (User)request.getSession().getAttribute("user");
        post.setUser_id(user.getUid());

        try {
            BeanUtils.populate(post,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = postService.savePost(post);
    }

}
