package cn.jyq.servlet;

import cn.jyq.domain.PageBean;
import cn.jyq.domain.User;
import cn.jyq.sevice.UserService;
import cn.jyq.sevice.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        //接收参数currentPage和rows
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示行数

        if(currentPage == null || "".equals(currentPage))
            currentPage = "1";
        if(rows == null || "".equals(rows))
            rows = "5";

        //获取查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //调用service方法查询PageBean
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows, condition);

        System.out.println(pb);

        //将pb对象存到request中
        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);

        //转发到userList
        request.getRequestDispatcher("/list.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
