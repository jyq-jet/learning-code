package cn.jyq.servlet;

import cn.jyq.domain.User;
import cn.jyq.sevice.UserService;
import cn.jyq.sevice.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //根据user的id查询用户信息
        String id = request.getParameter("id");
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);
        //设置属性并转发
        request.setAttribute("user", user);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
