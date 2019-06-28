package com.xinlan.service;

import com.xinlan.dao.DaoFactory;
import com.xinlan.dao.UserDao;
import com.xinlan.model.User;

public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao = DaoFactory.genUserDao();
    }

    public long addUser(User user){
        return userDao.addUser(user);
    }

    public User queryUser(long uid){
        return userDao.queryByUid(uid);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }

}//end class
