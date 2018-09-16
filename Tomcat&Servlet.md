# Tomcat&Servlet

[TOC]

## 一个基本的认识

### Tomcat（Servlet）容器

> Tomcat容器实际上也就是一个Servlet容器

在Tomcat启动后，会运行一个Servlet容器，Servlet容器要为一个Servlet请求提供服务，这些服务主要包括：

1. Servlet容器将Servlet请求封装成requst对象，并在request对象中填充Servlet请求的参数，包含header、cookies、params、body等。

   > 一个request对象是一个javax.servlet.ServletRequest 或 javax.servlet.http.ServletRequest 接口的一个实 例。

2. Servlet容器创建一个response对象，Servlet请求处理之后的结果会由response来发送给客户端进行响应。

   > 一个 response 对象 javax.servlet.ServletResponse 或 javax.servlet.http.ServletResponse 接口 的一个实例。 

3. Servlet容器会调用Servlet请求映射的一个Servlet类（这个类需要实现HttpServlet接口，并根据业务逻辑重写其中的service方法），并执行其中的service方法，把前面封装和创建好的request、response对象传入，在service方法中处理Servlet请求，从request中取值，并将结果写入response。

### Catalina容器

Catalina是Tomcat容器下的最外层组件，Catalina可以看作是由两个主要的模块组成：Connectors+Container。

![Catalina结构](/var/folders/02/6fb830cn5wx_blr0m3nj9bx40000gn/T/abnerworks.Typora/image-20180916142608925.png)

1. 一个Catalina组件由多个Connnectors和一个Container组成，其中，Connectors和Container是多对一的关系。
2. Connectors的主要职责是接收HTTPServlet请求，并构造成一个request和response对象，并将这两个对象传递给Container。
3. Container接收到request和response对象后，调用该请求映射的某个Servlet子类的service方法来处理HttpServlet请求。

## Web服务器

> 超文本传输协议（HTTP）服务器

Web服务器使用HTTP协议来跟客户端进行通信。一个基于Java的Web服务器主要使用两个重要的类：**java.net.Socket** 和 **java.net.ServerSocket**，并通过 HTTP 消息进行通信。

### HTTP（超文本传输协议）

- 一种允许web服务器和浏览器通过互联网进行数据请求和响应的协议。
- 一种请求+响应的协议。
- 使用可靠的TCP连接，TCP默认使用端口80.

在 HTTP 中，始终都是客户端通过建立连接和发送一个 HTTP 请求从而开启一个事务。web 服
务器不需要联系客户端或者对客户端做一个回调连接。无论是客户端或者服务器都可以提前终止
连接。举例来说，当你正在使用一个 web 浏览器的时候，可以通过点击浏览器上的停止按钮来停
止一个文件的下载进程，从而有效的关闭与 web 服务器的 HTTP 连接。

#### HTTP请求

一个完整的HTTP请求包含三部分：

1. HTTP方+ 统一资源标识符（URI）+协议/版本。
2. 请求头部
3. 主体内容

> 在请求方法+URI+协议/版本与请求头部之间没有空行隔离，在请求同步与请求主体内容（参数、body）之间有空行隔离。

![HTTP请求](/var/folders/02/6fb830cn5wx_blr0m3nj9bx40000gn/T/abnerworks.Typora/image-20180916144330489.png)

#### HTTP响应

类似于HTTP请求，一个HTTP响应包含三部分：

1. HTTP方+ 统一资源标识符（URI）+协议/版本。
2. 响应头部
3. 主体内容

![HTTP响应](/Users/yangkun/YoungGit/Noter-Young/resources/Jietu20180916-144746.jpg)

### Socket类

> 《Java网络编程》
>
> 客户端套接字
>
> 一个套接字=IP+端口号。是一台计算机对外暴露的可以用于与外界数据交互的入口。

套接字是网络连接的一个端点。套接字使得一个应用可以从网络中读取和写入数据。放在两 个不同计算机上的两个应用可以通过连接发送和接受字节流。为了从你的应用发送一条信息到另 一个应用，你需要知道另一个应用的 IP 地址和套接字端口。在 Java 里边，套接字指的是 java.net.Socket 类。

- 创建一个套接字，可以使用Socket类的若干构造方法。

  ```java
  public Socket (java.lang.String host, int port)
  new Socket ("yahoo.com", 80);
  ```

  在创建好套接字后，可以使用流的方式来发送/接收套接字数据。

  1. 向套接字远程地址发送字节流，需要调用Socket类的getOutPutStream方法。
  2. 接收套接字远程地址字节流，需要调用Socket类的getInputStream方法。

- 使用Socket类与本地localhsot：8080端口进行访问

  ```java
  package com.young.demo;
  
  import java.io.*;
  import java.net.Socket;
  
  public class Main {
  
      public static void main(String[] args) {
  	// write your code here
          try {
              Socket socket=new Socket("127.0.0.1",8080);
              OutputStream out = socket.getOutputStream();
              boolean autoFlush=true;
              PrintWriter printWriter=new PrintWriter(out,autoFlush);
              InputStream in=socket.getInputStream();
              BufferedReader reader=new BufferedReader(new InputStreamReader(in));
              //send a http request to server 127.0.0.1:8080
              printWriter.println("GET /index.jsp HTTP/1.1");
              printWriter.println("Host:localhost:8080");
              printWriter.println("Connection: Close");
              printWriter.println();
              //read the response
              boolean loop = true;
              StringBuffer sb = new StringBuffer(8096);
              while (loop) {
                  if ( reader.ready() ) {
                      int i=0;
                      while (i!=-1) {
                          i = in.read();
                          sb.append((char) i);
                      }
                      loop = false;
                  }
              }
              //display response to the console
              System.out.println(sb.toString());
              socket.close();
          } catch (IOException e) {
              System.out.print(e.getMessage());
          }
      }
  }
  ```

### ServerSocket类

> 服务端套接字



