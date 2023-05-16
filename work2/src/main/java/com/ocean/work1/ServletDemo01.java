package com.ocean.work1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 作业一
 * 1. 在web.xml里面配置了初始化参数，editor和date，通过getServletContext().getInitParameter("")方法进行获取
 * 2. 在web.xml使用了通配符配置的方法，通过访问路径/work1，或者*.ocean（*处可以输入任意值，只需要以.ocean结尾即可）
 */
public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String editor = getServletContext().getInitParameter("editor");
        String date = getServletContext().getInitParameter("date");
        System.out.println("editor: " + editor + "\n" + "date: " + date);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
