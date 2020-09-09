package cn.jyq.servlet;

import cn.jyq.domain.User;
import cn.jyq.sevice.UserService;
import cn.jyq.sevice.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 设置编码
        request.setCharacterEncoding("utf-8");
        //2. 获取数据
        //2.1 获取用户输入验证码
        String verifyCode = request.getParameter("verifycode");
        Map<String, String[]> map = request.getParameterMap();

        //暂时测试
        System.out.println("在登录页面获取到的map如下");
        for (String key:map.keySet()) {
            System.out.print(key + ": ");
            for (String value : map.get(key)) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        //验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if(checkcode_server != null && !checkcode_server.equalsIgnoreCase(verifyCode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg", "验证码错误");
            //跳转到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);

            return;

        }

        //验证码正确
        //封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(user);

        if(loginUser != null){
            //登录成功
            //登录成功，将用户存入session
            session.setAttribute("user", loginUser);
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg", "用户名或密码错误");
            //跳转到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
