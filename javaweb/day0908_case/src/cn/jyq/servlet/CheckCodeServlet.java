package cn.jyq.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 50;

        //在内存中创建一图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //美化图片
        //填充背景色
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,width,height);

        //画边框
        g.setColor(Color.BLUE);
        g.drawRect(0,0,width - 1, height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i =1 ; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("黑体",Font.BOLD,24));
            g.drawString(ch+"", width/5*i, height/2);
        }
        String checkCode_session = sb.toString();
        //将验证码存入session中
        req.getSession().setAttribute("CHECKCODE_SERVER", checkCode_session);

        //画干扰线
        g.setColor(Color.GREEN);
        //随机生成坐标点

        int lineNum = 10;
        for (int i = 0; i < lineNum; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);

            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        //将图片输出到页面展示
        ImageIO.write(image, "jpg",resp.getOutputStream());
    }
}
