package cn.jyq.sevice.impl;

import cn.jyq.dao.UserDao;
import cn.jyq.dao.impl.UserDaoImpl;
import cn.jyq.domain.User;
import cn.jyq.sevice.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用Dao进行查询
        return userDao.findAll();
    }

    @Override
    public User login(User loginUser) {
        return userDao.findUserByUsernameAndPassword(loginUser.getUsername(),loginUser.getPassword());
    }
}
