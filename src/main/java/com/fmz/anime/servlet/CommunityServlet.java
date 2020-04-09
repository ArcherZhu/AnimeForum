package com.fmz.anime.servlet;

import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.PageBean;
import com.fmz.anime.entity.User;
import com.fmz.anime.service.CommunityServiceImpl;
import com.fmz.anime.service.ICommunityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/community/*")
public class CommunityServlet extends BaseServlet {

    private ICommunityService communityService = new CommunityServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String fidStr = request.getParameter("fid");

        User testUser = (User)request.getSession().getAttribute("user");

        int fid = 0;
        if (fidStr != null && fidStr.length() > 0 && !"null".equals(fidStr)) {
            fid = Integer.parseInt(fidStr);
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

        PageBean<Community> commuPageBean = communityService.pageQuery(fid, currentPage, pageSize);
        writeValue(response, commuPageBean);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        int cid = 0;
        String communityName = "";
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        List<Community> cs = communityService.findOne(cid);
        //只有一个，查询社区名用
        if(cs.size()==1){
            communityName = cs.get(0).getCname();
        }
        writeValue(response, communityName);
    }

    /**
     * 增加帖子数
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addPostNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        int cid = 0;
        String communityName = "";
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        communityService.addPostNum(cid);
    }


}
