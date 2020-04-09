package com.fmz.anime.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fmz.anime.entity.Community;
import com.fmz.anime.entity.ResultInfo;
import com.fmz.anime.entity.User;
import com.fmz.anime.service.IUserService;
import com.fmz.anime.service.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.omg.CORBA.OBJ_ADAPTER;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class AllUserServlet extends BaseServlet{

    private IUserService service = new UserServiceImpl();
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("注册测试1： " + user.getNickname());
        //3.调用service完成注册
        boolean flag = service.register(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if (flag) {
            // 注册成功
            info.setFlag(true);
        } else {
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败！！！");
        }

        //将info对象序列化为json
        String json = writeValueAsString(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User u = service.login(user);
        ResultInfo info = new ResultInfo();

        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }else{
            info.setFlag(true);
            System.out.println("登录成功了");
        }
        //在session中添加用户
        request.getSession().setAttribute("user",u);
        request.getSession().setAttribute("usertest", "ccccc");
        //响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);
    }

    /**
     * 通过Id查找用户
     */
    public void findUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uidStr = request.getParameter("uid");
        int uid = 0;
        if (uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)) {
            uid = Integer.parseInt(uidStr);
        }
        User user = service.findUserById(uid);

        writeValue(response, user);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
