package cn.jyq.sevice;

import cn.jyq.domain.PageBean;
import cn.jyq.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param loginUser
     * @return
     */
    public User login(User loginUser);

    /**
     * 添加用户方法
     * @param user
     */
    public void addUser(User user);

    /**
     * 删除用户方法
     * @param id
     */
    public void deleteUser(String id);

    /**
     * 根据id值查询用户信息，并返回User对象
     * @param id
     * @return
     */
    public User findUserById(String id);

    /**
     * 利用封装好的user
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据用户id批量删除用户
     * @param ids
     */
    public void deleteSelected(String[] ids);

    /**
     * 分页查询用户
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
