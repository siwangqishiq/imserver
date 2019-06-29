package com.xinlan.dao;

import com.xinlan.model.User;

import java.util.List;

public interface UserDao {

    long addUser(User user);

    void updateUser(User user);

    User queryByUid(long uid);

    List<User> queryByAccount(String account);
}
