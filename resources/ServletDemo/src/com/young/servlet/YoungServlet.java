package com.young.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class YoungServlet extends javax.servlet.http.HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("第一个请求进入，初始化Servlet实现类");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("this is service method");
        resp.setContentType("text/html");
        Cookie username=new Cookie("username",req.getParameter("username"));
        Cookie password=new Cookie("password",req.getParameter("password"));
        username.setMaxAge(30);
        password.setMaxAge(15);
        resp.addCookie(username);
        resp.addCookie(password);
        PrintWriter toBrowser=resp.getWriter();
        String title = "设置 Cookies 实例";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +       "transitional//en\">\n";
        toBrowser.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>名字</b>："
                + req.getParameter("first_name") + "\n" +
                "  <li><b>姓氏</b>："
                + req.getParameter("last_name") + "\n" +
                "</ul>\n" +
                "</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("tomcat容器关闭，销毁Servlet实现类");
    }
}
