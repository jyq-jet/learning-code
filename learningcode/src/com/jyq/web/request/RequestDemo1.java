package com.jyq.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/requestDemo1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求方式
        String method = req.getMethod();
        System.out.println("reqMethod: " + method);

        //2. 获取虚拟目录
        String contextPath = req.getContextPath();
        System.out.println("contextPath: " + contextPath);

        //3. 获取Servlet路径
        String servletPath = req.getServletPath();
        System.out.println("servletPath: " + servletPath);

        //4. 获取get方式请求参数
        String queryString = req.getQueryString();
        System.out.println("queryString: " + queryString);

        //5. 获取请求URI
        String requestURI = req.getRequestURI();
        StringBuffer requestURL = req.getRequestURL();
        System.out.println("requestURI: " + requestURI);
        System.out.println("requestURL: " + requestURL);

        //6. 获取协议及版本
        String protocol = req.getProtocol();
        System.out.println("protocol: " + protocol);

        //7. 获取客户机的ip地址
        String remoteAddr = req.getRemoteAddr();
        System.out.println("remoteAddr: " + remoteAddr);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
