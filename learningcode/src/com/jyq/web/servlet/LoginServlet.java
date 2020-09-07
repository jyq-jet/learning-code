package com.jyq.web.servlet;

import com.jyq.web.dao.UserDao;
import com.jyq.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpCookie;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        //先判断验证码是否正确

        //获取session
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //删除session中的验证码
        session.removeAttribute("checkCode_session");
        if(checkCode_session != null && checkCode_session.equalsIgnoreCase(checkCode)){
            //验证码正确
            User loginUser = new User(username,password);
            UserDao userDao = new UserDao();
            User user = userDao.login(loginUser);
            if(user != null){
                session.setAttribute("user", username);
                //重定向到success.jsp
                System.out.println("即将访问");
                System.out.println(req.getContextPath() + "/success.jsp");
                resp.sendRedirect(req.getContextPath() + "/success.jsp");
            }else{
                //登录失败
                req.setAttribute("login_error","用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }

        }else{
            //验证码错误，转发到登录页面
            req.setAttribute("cc_error","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }


//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);
//
//        UserDao userDao = new UserDao();
//        User user = userDao.login(loginUser);
//
//        if(user == null){
//            //登录失败
//            req.getRequestDispatcher("/failServlet").forward(req, resp);
//        }else{
//            //登录成功
//            req.setAttribute("user", user);
//            req.getRequestDispatcher("/successServlet").forward(req, resp);
//        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
