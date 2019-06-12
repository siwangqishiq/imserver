package com.xinlan.dao;

import com.xinlan.dao.impl.UserDaoImpl;

public class DaoFactory {
    public static UserDao createUserDao() {
        return new UserDaoImpl();
    }
}
