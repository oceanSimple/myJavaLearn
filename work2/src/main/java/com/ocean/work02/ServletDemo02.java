package com.ocean.work02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 在登录界面进行登录
 * 当账号密码均为admin（不区分大小写）,登录成功
 * 若登录成功，则通过请求转发的方式，跳转请求路径“/success”，打印success与用户名
 * 若登录失败，则通过重定向方式，跳转error.html界面，告知用户登录失败
 */
@WebServlet("/login")
public class ServletDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        if ("admin".equalsIgnoreCase(account) && "admin".equalsIgnoreCase(password)) {
            // 登录成功，则使用请求转发的方法，进行页面跳转
            req.getRequestDispatcher("/success").forward(req,resp);
        }else {
            // 登录失败，使用重定向的方法，进行页面跳转
            resp.sendRedirect("/error.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
