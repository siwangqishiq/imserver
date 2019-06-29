package com.xinlan.dao.impl;

import com.xinlan.dao.UserDao;
import com.xinlan.model.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoImpl implements UserDao {
    public SqlSession sqlSession;

    public UserDaoImpl(SqlSession session){
        sqlSession = session;
    }

    @Override
    public long addUser(User user) {
        int ret = sqlSession.insert("User.add" , user);
        if(ret > 0){
            return user.getUid();
        }
        return ret;
    }

    @Override
    public User queryByUid(long uid) {
        return sqlSession.selectOne("User.select_by_uid",uid);
    }

    @Override
    public List<User> queryByAccount(String account) {
        return sqlSession.selectList("User.select_by_account" , account);
    }

    @Override
    public void updateUser(User user) {
    }
}
