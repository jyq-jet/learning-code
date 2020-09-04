package com.jyq.web.test;

import com.jyq.web.dao.UserDao;
import com.jyq.web.domain.User;
import org.junit.Test;


public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("小明");
        loginUser.setPassword("123");

        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);



    }





}
