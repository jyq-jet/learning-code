package com.jyq.web.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;

    static {
        try {
            //1. 加载配置文件
            Properties properties = new Properties();
            //使用classloader加载配置文件，获取字节输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            System.out.println("配置文件加载成功");
            properties.load(is);
            Enumeration pro = properties.propertyNames();
            while(pro.hasMoreElements()){
                String key = (String)pro.nextElement();
                System.out.println(key + ':' + properties.getProperty(key));
            }
            //2. 初始化连接池
            ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDatasource(){
        return ds;
    }

    /**
     * 获取Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
