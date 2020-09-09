package cn.jyq.servlet;

import cn.jyq.sevice.UserService;
import cn.jyq.sevice.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取待删除用户id
        String id = request.getParameter("id");
        //调用service的删除方法删除用户
        UserService service = new UserServiceImpl();
        service.deleteUser(id);
        //重定向到UserListServlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
