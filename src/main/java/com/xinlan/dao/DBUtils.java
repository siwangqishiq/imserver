package com.xinlan.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBUtils {
    private static SqlSession sqlSession = null;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            //设置为true 自动提交事务
            sqlSession = sqlMapper.openSession(true);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSession;
    }
}
