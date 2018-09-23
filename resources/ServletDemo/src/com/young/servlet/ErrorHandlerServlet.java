package com.young.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理Servlet异常，通过实现Servlet接口实现
 * 在web.xml中，配置异常处理的Servlet实现类，以及对应的url-pattern
 * 此外，根据需要处理的错误类型，HTTP响应码/异常定义不同的异常处理地址，这部分的定义在error-page中
 */
public class ErrorHandlerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 分析 Servlet 异常
        Throwable throwable = (Throwable)
                req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                req.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null){
            servletName = "Unknown";
        }
        String requestUri = (String)
                req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null){
            requestUri = "Unknown";
        }

        // 设置响应内容类型
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        String title = "Error/Exception Information";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +       "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n");

        if (throwable == null && statusCode == null){
            out.println("<h2>Error information is missing</h2>");
            out.println("Please return to the <a href=\"" +             resp.encodeURL("http://localhost:8080/") +             "\">Home Page</a>.");
        }else if (statusCode != null){
            out.println("The status code : " + statusCode);
        }else{
            out.println("Servlet Name : " + servletName +
                    "</br></br>");
            out.println("Exception Type : " +
                    throwable.getClass( ).getName( ) +
                    "</br></br>");
            out.println("The request URI: " + requestUri +
                    "<br><br>");
            out.println("The exception message: " +
                    throwable.getMessage( ));
        }
        out.println("</body>");
        out.println("</html>");
    }

}
