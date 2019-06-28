package com.xinlan.dao;

import com.xinlan.dao.impl.UserDaoImpl;

public class DaoFactory {
    public static UserDao genUserDao() {
        return new UserDaoImpl(DBUtils.getSqlSession());
    }

}
