package com.xinlan.dao.impl;

import com.xinlan.dao.UserDao;
import com.xinlan.model.User;
import org.apache.ibatis.session.SqlSession;

public class UserDaoImpl implements UserDao {
    public SqlSession sqlSession;

    public UserDaoImpl(SqlSession session){
        sqlSession = session;
    }

    @Override
    public long addUser(User user) {
        return 0;
    }

    @Override
    public long updateUser(User user) {
        return 0;
    }
}
