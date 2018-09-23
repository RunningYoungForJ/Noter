package com.young.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

/**
 * Servlet过滤器通过实现javax.servlet.Filter类来创建
 * init：由Tomcat容器调用，提示一个过滤器被加载进服务,在Tomcat容器启动的时候，过滤器就会被加载，不是等到第一个请求时再加载
 * destroy：由Tomcat容器调用，提示一个过滤器从服务中销毁
 * doFilter：对具体url请求在进入service前拦截请求，并执行doFilter内容
 *
 * Filter的执行顺序：根据web.xml中filter-mapping的定义顺序来执行。
 *
 *
 */
public class YoungFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("destroy filter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String ip=req.getRemoteAddr();
        System.out.println("IP: "+ip+" time: "+new Date().toString());
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init filter");
    }

}
