package cn.jyq.servlet;

import cn.jyq.sevice.UserService;
import cn.jyq.sevice.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class delSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取id数组
        String[] ids = request.getParameterValues("uid");
        System.out.println(ids);

        //调用UserService中的方法
        UserService service = new UserServiceImpl();
        service.deleteSelected(ids);

        //跳转到查询所有userListServlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
