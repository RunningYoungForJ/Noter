package com.young.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    private boolean isMultipart;
    private String filePath;
    private File file;

    @Override
    public void init() throws ServletException {
        this.filePath=getServletContext().getInitParameter("file-upload");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的enctype类型
        isMultipart= ServletFileUpload.isMultipartContent(req);
        resp.setContentType("text/html");
        PrintWriter toBrowser=resp.getWriter();
        if (isMultipart){
            //创建数据流处理工厂-作用是封装数据流为一个FileItem对象
            DiskFileItemFactory diskFactory=new DiskFileItemFactory();
            //创建上传Servlet服务
            ServletFileUpload servletFileUpload=new ServletFileUpload(diskFactory);
            try {
                //使用factory封装上传文件的数据流，并保存在临时路径下，即DiskFileItemFactory的repository属性
                //如果有多个文件同时上传，则处理多个
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                Iterator it=fileItems.iterator();
                toBrowser.println("<html>");
                toBrowser.println("<head>");
                toBrowser.println("<title>Servlet upload</title>");
                toBrowser.println("</head>");
                toBrowser.println("<body>");
                while (it.hasNext()){
                    //获取每一个文件对象
                    FileItem item=(FileItem)it.next();
                    String fieldName=item.getFieldName();
                    String fileName=item.getName();
                    String contentType=item.getContentType();
                    boolean isInMemory=item.isInMemory();
                    long sizeByBytes = item.getSize();
                    file=new File(filePath+fileName);
                    //将上传文件写入指定路径file内
                    item.write(file);
                    toBrowser.println("Uploaded Filename: " + fileName + "<br>");
                }
                toBrowser.println("</body>");
                toBrowser.println("</html>");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            resp.sendRedirect();
        }
    }
}
