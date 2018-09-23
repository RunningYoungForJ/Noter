<%--
  Created by IntelliJ IDEA.
  User: yangkun
  Date: 2018/9/23
  Time: 下午4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ServletDemo</title>
  </head>
  <body>
    <form action="/demo" method="post">
      <label>用户名：</label>
      <input type="text" placeholder="用户名" name="username">
      <br>
      <label>密码: </label>
      <input type="password" placeholder="密码" name="password">
      <br>
      <input type="submit" name="提交">
    </form>

    <h3>文件上传</h3>
    请选择要上传的文件:<br>
    <form method="post" action="/upload" enctype="multipart/form-data">
      <input type="file" name="file" size="50">
      <br>
      <input type="submit" value="文件上传">

    </form>
  </body>
</html>
