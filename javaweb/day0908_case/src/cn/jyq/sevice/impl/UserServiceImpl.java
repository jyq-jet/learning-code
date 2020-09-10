package cn.jyq.sevice.impl;

import cn.jyq.dao.UserDao;
import cn.jyq.dao.impl.UserDaoImpl;
import cn.jyq.domain.PageBean;
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

    @Override
    public void deleteSelected(String[] ids) {
        if(ids != null && ids.length > 0){
            for(String id : ids){
                userDao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<User> pb = new PageBean<User>();



        //先查询总记录数
        int totalCount = userDao.findTotalCount();

        //计算currentPage的边界，也就是总页数
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;

        if(currentPage <= 0)
            currentPage = 1;
        if(currentPage > totalPage)
            currentPage = totalPage;

        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //计算开始查询的索引
        int start = (currentPage - 1) * rows;


        List<User> list = userDao.findByPage(start, rows);

        //设置参数
        pb.setTotalCount(totalCount);
        pb.setList(list);
        pb.setTotalPage(totalPage);

        if(pb.getCurrentPage() > pb.getTotalPage())
            pb.setCurrentPage(pb.getTotalPage());

        return pb;
    }
}
