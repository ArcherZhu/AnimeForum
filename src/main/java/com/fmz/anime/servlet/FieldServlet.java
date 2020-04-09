package com.fmz.anime.servlet;

import com.fmz.anime.entity.Field;
import com.fmz.anime.service.FieldServiceImpl;
import com.fmz.anime.service.IFieldService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/field/*")
public class FieldServlet extends BaseServlet{
    private IFieldService service = new FieldServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Field> cs = service.findAll();
        writeValue(response, cs);
    }

    /**
     * 查询板块名称
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        int fid = 0;
        String zoneName = "";
        if (fidStr != null && fidStr.length() > 0 && !"null".equals(fidStr)) {
            fid = Integer.parseInt(fidStr);
        }
        List<Field> cs = service.findOne(fid);
        if(cs.size()==1){
            zoneName = cs.get(0).getFname();
        }
        writeValue(response, zoneName);
    }

}
