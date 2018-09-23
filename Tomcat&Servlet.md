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

## Servlet

### Servlet生命周期

> Tomcat服务器启动后，项目的web.xml加载进内存，但xml中具体内容的解析并不是在启动时就执行。

生命周期：Servlet初始化（init）--->Servlet销毁（destory）

1. Servlet初始化：当第一次请求发生时，Servlet加载进内存时，只执行一次init方法。（在整个Servlet生命周期中，init方法只会执行一次，当且仅当对应的第一次请求发生时）
2. Servlet销毁：Tomcat服务器关闭时，执行所有Servlet的destory方法，销毁Servlet。

### 提前初始化（load-on-startup）

如果Servlet在web.xml中配置了load-on-startup，则Servlet生命周期为Tomcat服务器启动掉关闭，其中load-on-startup的参数仅仅影响Tomcat服务器启动时Servlet的初始化加载顺序。

### service、doGet、doPost方法

> 都是HttpServlet类中的方法。

1. service方法既可以处理Get，又可以处理Post请求。
2. doGet方法处理Get请求。
3. doPost方法处理Post请求，如果Servlet请求的请求方法与url映射的Servlet方法请求方式不一样，会报405错误。
4. 如果在Servlet类中，如果同时重写了service、doGet、doPost方法，都会优先执行service方法，然后根据请求方式，执行doGet或doPost方法。（如果在service中调用了父类的service方法，super.service，没有super.service方法的话，就只会执行service）

### 常见的请求错误

| 错误代码 | 含义            | 原因                                                         |
| -------- | --------------- | ------------------------------------------------------------ |
| 404      | 资源未找到      | url地址错误，在web.xml中找不到请求url对应的servlet请求       |
| 500      | class not found | 1. 在web.xml中url对应的Servlet类路径错误<br />2. 对应请求的service方法逻辑错误 |
| 405      | 请求方式不支持  | Servlet请求方式与Servlet类的对应方法不匹配                   |

### HttpServletRequest请求

Tomcat服务器每接收一个请求，就创建一个Request对象来存储请求的所有数据，并将Request对象传递给service方法处理。

#### ServletRequest请求乱码

> 浏览器默认的数据编码格式是ISO-8859-1，后台服务一般的编码是UTF-8。

1. 使用String进行重新编码。

   - 先使用浏览器的默认编码格式进行解码
   - 然后使用服务端的默认编码进行编码。

   ```java
   new String(str.getBytes("iso-8859-1"),"utf-8")
   ```



#### Session概念



### Servlet处理文件上传

> Servlet与HTML form表单一起使用，支持上传文件/图像到服务器。

需要注意的是：

- 表单method方法应该是POST
- 表单enctype应该是multipart/form-data
- 表单action应该是后端负责处理上传逻辑的Servlet文件

文件上传需要依赖apache-common的两个jar包：

- commons-fileupload-1.3.3.jar
- commons-io-2.6.jar

因为Servlet是运行在Tomcat服务器下，因此程序需要的额外jar包也只能放在Tomcat服务器lib下，放在磁盘其它地方，会报ClassNotFound错误。

```java
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
        }
    }
}
```













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

服务端套接字需要等待客户端的请求，一旦服务端套接字获得了一个客户端请求，就会创建一个Socket实例与客户端进行通信。

要创建一个服务器套接字，你需要使用 ServerSocket 类提供的四个构造方法中的一个。你
需要指定 IP 地址和服务器套接字将要进行监听的端口号。通常，IP 地址将会是 127.0.0.1，也
就是说，服务器套接字将会监听本地机器。服务器套接字正在监听的 IP 地址被称为是绑定地址。
服务器套接字的另一个重要的属性是 backlog，这是服务器套接字开始处理传入的请求之前，传
入的连接请求的最大队列长度。

```java
public ServerSocket(int port, int backLog, InetAddress bindingAddress);
new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));
```

- ServerSocket示例

  ```java
  public class Server {  
    
      public static void main(String[] args) throws Exception{  
            
          // port:8888,backlog:5  
          ServerSocket server = new ServerSocket(8888, 5);  
            
          int acceptCount = 0;  
            
          while(true)  
          {  
              Socket client = server.accept();  
                 
              acceptCount++;  
                 
               System.out.println("new connection has connected, num=" + acceptCount);  
          }  
                 
      }  
  }  
  ```

  accept方法：从客户端socket发起的请求连接中选择一个，并建立与服务端的连接。backlog维护了客户端请求等待队列的大小。

## 一个简单的Servlet容器

> Servlet 编程是通过 javax.servlet 和 javax.servlet.http 这两个包的类和接口来实现的。
> 其中一个至关重要的就是 javax.servlet.Servlet 接口了。**所有的 servlet 必须实现实现或者继**
> **承实现该接口的类。**

### javax.servlet.Servlet

Servlet接口有五个方法

```java
public void init(ServletConfig config) throws ServletException
public void service(ServletRequest request, ServletResponse response)
throws ServletException, java.io.IOException 
public void destroy()
public ServletConfig getServletConfig()
public java.lang.String getServletInfo()
```

init、service、destory方法是Servlet生命周期的方法。

- 在Servlet容器初始化后，init方法将会在Servlet请求第一次访问的时候被调用，而且在整个生命周期中只调用这一次，将对应的Servlet实现类的class文件加载到Servlet容器中。
- Servlet容器使用service方法处理Servlet请求，每一个Servlet请求进入容器后，都会由容器传递一个javax.servlet.ServletRequest 对象和 javax.servlet.ServletResponse 对象。ServletRequest 对象包括客户端的 HTTP 请求信息，而 ServletResponse 对象封装 servlet 的响应。在 servlet 的生命周期中，service 方法将会给调用多次。
- destory方法发生在Servlet容器关闭的时候。

### 一个简单的Servlet容器需要做的事情

正如在第一章中所描述的那样，Tomcat（Servlet）所具有的功能，简单说来，就是以下几个：

1. 接收请求，封装为ServletRequest和ServletResponse
2. 调用对应Servlet实现类的service方法处理

基于这样的功能，我们可以整理出一个简单的实现过程，如下：

- 能够接收请求，基于java提供的SeverSocket类，监听某一host地址的某一端口，如果有请求，则调用accpet方法建立连接，并处理请求。

  ```java
  package cxs02.pyrmont;
  
  import java.net.Socket;
  import java.net.ServerSocket;
  import java.net.InetAddress;
  import java.io.InputStream;
  import java.io.OutputStream;
  import java.io.IOException;
  
  public class HttpServer1 {
  
    /** WEB_ROOT is the directory where our HTML and other files reside.
     *  For this package, WEB_ROOT is the "webroot" directory under the working
     *  directory.
     *  The working directory is the location in the file system
     *  from where the java command was invoked.
     */
    // shutdown command,关闭ServerSocket连接
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
  
    // the shutdown command received
    private boolean shutdown = false;
  
    public static void main(String[] args) {
      HttpServer1 server = new HttpServer1();
      //启动服务端监听
      server.await();
    }
  
    public void await() {
      ServerSocket serverSocket = null;
      int port = 8080;
      try {
        //创建一个本地127.0.0.1:8080端口的服务端地址
        serverSocket =  new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
      }
      catch (IOException e) {
        e.printStackTrace();
        System.exit(1);
      }
  
      // Loop waiting for a request
      while (!shutdown) {
        Socket socket = null;
        InputStream input = null;
        OutputStream output = null;
        try {
          //accpet会监听请求（客户端socket请求），并建立连接
          socket = serverSocket.accept();
          //获取服务端接收到的数据（客户端socket传入的）
          input = socket.getInputStream();
          //获取服务端发送的数据（客户端socket要接收的）
          output = socket.getOutputStream();
  
          // 构建一个ServletResquest实现类的实例
          Request request = new Request(input);
          request.parse();
  
          // 构建一个ServletResponse实现类的实例
          Response response = new Response(output);
          response.setRequest(request);
  
          // check if this is a request for a servlet or a static resource
          // a request for a servlet begins with "/servlet/"
          //如果是/servlet开头的url，交给Servlet实现类去处理
          if (request.getUri().startsWith("/servlet/")) {
            ServletProcessor1 processor = new ServletProcessor1();
            processor.process(request, response);
          }
          else {
            StaticResourceProcessor processor = new StaticResourceProcessor();
            processor.process(request, response);
          }
  
          // Close the socket
          socket.close();
          //check if the previous URI is a shutdown command
          shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
        }
        catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
        }
      }
    }
  }
  ```

- 实现ServletResquest，用于封装Servlet请求

  ```java
  package  cxs02.pyrmont;
  
  import java.io.InputStream;
  import java.io.IOException;
  import java.io.BufferedReader;
  import java.io.UnsupportedEncodingException;
  import java.util.Enumeration;
  import java.util.Locale;
  import java.util.Map;
  import javax.servlet.RequestDispatcher;
  import javax.servlet.ServletInputStream;
  import javax.servlet.ServletRequest;
  
  
  public class Request implements ServletRequest {
  
    private InputStream input;
    private String uri;
  
    public Request(InputStream input) {
      this.input = input;
    }
  
    public String getUri() {
      return uri;
    }
  
    private String parseUri(String requestString) {
      int index1, index2;
      index1 = requestString.indexOf(' ');
      if (index1 != -1) {
        index2 = requestString.indexOf(' ', index1 + 1);
        if (index2 > index1)
          return requestString.substring(index1 + 1, index2);
      }
      return null;
    }
  
    public void parse() {
      // Read a set of characters from the socket
      StringBuffer request = new StringBuffer(2048);
      int i;
      byte[] buffer = new byte[2048];
      try {
        i = input.read(buffer);
      }
      catch (IOException e) {
        e.printStackTrace();
        i = -1;
      }
      for (int j=0; j<i; j++) {
        request.append((char) buffer[j]);
      }
      System.out.print(request.toString());
      uri = parseUri(request.toString());
    }
  
    /* implementation of the ServletRequest*/
    public Object getAttribute(String attribute) {
      return null;
    }
  
    public Enumeration getAttributeNames() {
      return null;
    }
  
    public String getRealPath(String path) {
      return null;
    }
  
    public RequestDispatcher getRequestDispatcher(String path) {
      return null;
    }
  
    public boolean isSecure() {
      return false;
    }
  
    public String getCharacterEncoding() {
      return null;
    }
  
    public int getContentLength() {
      return 0;
    }
  
    public String getContentType() {
      return null;
    }
  
    public ServletInputStream getInputStream() throws IOException {
      return null;
    }
  
    public Locale getLocale() {
      return null;
    }
  
    public Enumeration getLocales() {
      return null;
    }
  
    public String getParameter(String name) {
      return null;
    }
  
    public Map getParameterMap() {
      return null;
    }
  
    public Enumeration getParameterNames() {
      return null;
    }
  
    public String[] getParameterValues(String parameter) {
      return null;
    }
  
    public String getProtocol() {
      return null;
    }
  
    public BufferedReader getReader() throws IOException {
      return null;
    }
  
    public String getRemoteAddr() {
      return null;
    }
  
    public String getRemoteHost() {
      return null;
    }
  
    public String getScheme() {
     return null;
    }
  
    public String getServerName() {
      return null;
    }
  
    public int getServerPort() {
      return 0;
    }
  
    public void removeAttribute(String attribute) {
    }
  
    public void setAttribute(String key, Object value) {
    }
  
    public void setCharacterEncoding(String encoding)
      throws UnsupportedEncodingException {
    }
  
  }
  ```

- 实现ServletResponse，用于封装Servlet响应

  ```java
  package cxs02.pyrmont;
  
  import java.io.File;
  import java.io.FileInputStream;
  import java.io.FileNotFoundException;
  import java.io.IOException;
  import java.io.OutputStream;
  import java.io.PrintWriter;
  import java.util.Locale;
  import javax.servlet.ServletOutputStream;
  import javax.servlet.ServletResponse;
  
  public class Response implements ServletResponse {
  
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    PrintWriter writer;
  
    public Response(OutputStream output) {
      this.output = output;
    }
  
    public void setRequest(Request request) {
      this.request = request;
    }
  
    /* This method is used to serve a static page */
    public void sendStaticResource() throws IOException {
      byte[] bytes = new byte[BUFFER_SIZE];
      FileInputStream fis = null;
      try {
        /* request.getUri has been replaced by request.getRequestURI */
        File file = new File(Constants.WEB_ROOT, request.getUri());
        fis = new FileInputStream(file);
        /*
           HTTP Response = Status-Line
             *(( general-header | response-header | entity-header ) CRLF)
             CRLF
             [ message-body ]
           Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
        */
        int ch = fis.read(bytes, 0, BUFFER_SIZE);
        while (ch != -1) {
          output.write(bytes, 0, ch);
          ch = fis.read(bytes, 0, BUFFER_SIZE);
        }
      } catch (FileNotFoundException e) {
        String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length: 23\r\n" +
            "\r\n" +
            "<h1>File Not Found</h1>";
        output.write(errorMessage.getBytes());
      } finally {
        if (fis != null) {
          fis.close();
        }
      }
    }
  
  
    /**
     * implementation of ServletResponse
     */
    public void flushBuffer() throws IOException {
    }
  
    public int getBufferSize() {
      return 0;
    }
  
    public String getCharacterEncoding() {
      return null;
    }
  
    public Locale getLocale() {
      return null;
    }
  
    public ServletOutputStream getOutputStream() throws IOException {
      return null;
    }
  
    public PrintWriter getWriter() throws IOException {
      // autoflush is true, println() will flush,
      // but print() will not.
      writer = new PrintWriter(output, true);
      return writer;
    }
  
    public boolean isCommitted() {
      return false;
    }
  
    public void reset() {
    }
  
    public void resetBuffer() {
    }
  
    public void setBufferSize(int size) {
    }
  
    public void setContentLength(int length) {
    }
  
    public void setContentType(String type) {
    }
  
    public void setLocale(Locale locale) {
    }
  }
  ```

- Servlet请求最后交给Servlet实现类的service方法去处理，因此，需要根据ServletName来反射创建Servlet实现类，并执行service方法。

  ```java
  package cxs02.pyrmont;
  
  import java.io.File;
  import java.io.IOException;
  import java.net.URL;
  import java.net.URLClassLoader;
  import java.net.URLStreamHandler;
  import javax.servlet.Servlet;
  import javax.servlet.ServletRequest;
  import javax.servlet.ServletResponse;
  
  public class ServletProcessor1 {
  
    public void process(Request request, Response response) {
  
      String uri = request.getUri();
      String servletName = uri.substring(uri.lastIndexOf("/") + 1);
      URLClassLoader loader = null;
  
      try {
        // create a URLClassLoader
        //生成一个类加载器
        URL[] urls = new URL[1];
        URLStreamHandler streamHandler = null;
        File classPath = new File(Constants.WEB_ROOT);
        // the forming of repository is taken from the createClassLoader method in
        // org.apache.catalina.startup.ClassLoaderFactory
        String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator))
            .toString();
        // the code for forming the URL is taken from the addRepository method in
        // org.apache.catalina.loader.StandardClassLoader class.
        urls[0] = new URL(null, repository, streamHandler);
        loader = new URLClassLoader(urls);
      } catch (IOException e) {
        System.out.println(e.toString());
      }
      Class myClass = null;
      try {
        //使用类加载器，根据ServletName反射出Servlet实现类class
        myClass = loader.loadClass(servletName);
      } catch (ClassNotFoundException e) {
        System.out.println(e.toString());
      }
  
      Servlet servlet = null;
  
      try {
        //反射获取Servlet实现类实例
        servlet = (Servlet) myClass.newInstance();
        //调用Servlet实现类实例的service方法，处理Servlet请求
        servlet.service((ServletRequest) request, (ServletResponse) response);
      } catch (Exception e) {
        System.out.println(e.toString());
      } catch (Throwable e) {
        System.out.println(e.toString());
      }
  
    }
  }
  ```

## 连接器是个什么东西

一个符合规范的连接器必须创建javax.servlet.http.HttpServletRequest 和
javax.servlet.http.HttpServletResponse，并传递给被调用的 servlet 的 service 方法。

