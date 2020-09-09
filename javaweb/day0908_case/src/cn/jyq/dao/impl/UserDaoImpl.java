package cn.jyq.dao.impl;

import cn.jyq.dao.UserDao;
import cn.jyq.domain.User;
import cn.jyq.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDatasource());

    @Override
    public List<User> findAll() {
        //使用jdbc操作数据库
        //定义sql语句
        String sql = "select * from user";
        List<User> users = template.query(sql,new BeanPropertyRowMapper<User>(User.class));

        //调用Dao完成查询
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        String sql = "insert into user values (null,?,?,?,?,?,?,null,null)";
        template.update(sql, user.getName(),user.getGender(),user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail());

    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
        return user;
    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?, gender = ?, age = ?, address = ?, qq = ?,email = ? " +
                "where id = ?";
        template.update(sql, user.getName(),user.getGender(),user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }
}
