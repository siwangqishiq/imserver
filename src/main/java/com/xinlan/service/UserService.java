package com.xinlan.service;

import com.xinlan.dao.DaoFactory;
import com.xinlan.dao.UserDao;
import com.xinlan.exception.CommonException;
import com.xinlan.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService(){
        userDao = DaoFactory.genUserDao();
    }

    public long addUser(User user){
        return userDao.addUser(user);
    }

    public User addUser(String account , String pwd , String avatar ,int male) throws CommonException {
        User u = queryUserByAccount(account);
        if(u != null){
            throw new CommonException("此用户名已存在");
        }

        User user = new User();
        user.setAccount(account);
        user.setPwd(pwd);
        user.setAvatar(avatar);
        user.setState(male);

        long uid = addUser(user);
        user.setUid(uid);
        user.setPwd(null);
        return user;
    }

    public User queryUser(long uid){
        return userDao.queryByUid(uid);
    }

    public User queryUserByAccount(String account){
        List<User> list = userDao.queryByAccount(account);
        if(list != null && list.size() > 0)
            return list.get(0);

        return null;
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }

}//end class
