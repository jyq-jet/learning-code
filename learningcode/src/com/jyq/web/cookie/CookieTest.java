package com.jyq.web.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //获取所有cookie
        Cookie[] cookies = req.getCookies();

        //判断是否有包含上次登录时间的cookie
        boolean flag = false;

        if(cookies != null && cookies.length > 0){
            for (Cookie cookie:cookies) {
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    //有该cookie，不是第一次访问
                    flag = true;
                    //设置时间，写回当前时间数据
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);
                    System.out.println("编码前：" + str_date);
                    str_date = URLEncoder.encode(str_date,"utf-8");
                    System.out.println("编码后：" +str_date);
                    cookie.setValue(str_date);

                    //设置cookie的存活时间
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    //响应数据
                    resp.addCookie(cookie);


                    String str_lastTime = cookie.getValue();
                    System.out.println("解码前：" + str_lastTime);
                    str_lastTime = URLDecoder.decode(str_lastTime,"utf-8");
                    System.out.println("解码后：" + str_lastTime);
                    //写回cookie
                    resp.getWriter().write("<h1>欢迎回来，您上次的访问时间是：" + str_lastTime + "</h1>");
                }
            }
        }

        if (cookies == null || cookies.length == 0 || !flag){
            //首次访问

            //设置时间，写回当前时间数据
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);
            System.out.println("编码前：" + str_date);
            str_date = URLEncoder.encode(str_date, "utf-8");
            System.out.println("编码后：" + str_date);
            Cookie cookie = new Cookie("lastTime",str_date);
            //设置cookie的存活时间
            cookie.setMaxAge(60 * 60 * 24 * 30);
            //响应数据
            resp.addCookie(cookie);

            resp.getWriter().write("<h1>您好，欢迎首次访问</h1>");

        }
    }
}
