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

    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return userDao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }
}
