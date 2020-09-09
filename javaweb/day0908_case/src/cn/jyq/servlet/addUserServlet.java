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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class addUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码格式
        request.setCharacterEncoding("utf-8");
        //获取参数
        Map<String, String[]> map = request.getParameterMap();
        //暂时测试
        System.out.println("在添加用户页面获取到的map如下");
        for (String key:map.keySet()) {
            System.out.print(key + ": ");
            for (String value : map.get(key)) {
                System.out.print(value + " ");
            }
        }
        //封装对象
        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service保存
        UserService service = new UserServiceImpl();
        service.addUser(user);

        //跳转到userListServlet
        response.sendRedirect(request.getContextPath()+"/userListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request, response);
    }
}
