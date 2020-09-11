package cn.jyq.dao.impl;

import cn.jyq.dao.UserDao;
import cn.jyq.domain.User;
import cn.jyq.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //定义模板初始sql
        String sql = "select count(*) from user where 1 = 1";

        StringBuilder sb = new StringBuilder(sql);

        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for(String key : condition.keySet()){
            String value = condition.get(key)[0];
            if("currentPage".equals(key) || "rows".equals(key))
                continue;
            if(value != null && !"".equals(value)){

            }
            sb.append(" and " + key + " like ? ");
            params.add("%" + value + "%");
        }
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for(String key : condition.keySet()){
            String value = condition.get(key)[0];
            if("currentPage".equals(key) || "rows".equals(key))
                continue;
            if(value != null && !"".equals(value)){

            }
            sb.append(" and " + key + " like ? ");
            params.add("%" + value + "%");
        }
        //添加分页查询
        sb.append(" limit ? , ?");
        params.add(start);
        params.add(rows);
        sql = sb.toString();

        System.out.println(sql);
        System.out.println(params);

        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
        return list;
    }
}
