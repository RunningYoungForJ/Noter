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
