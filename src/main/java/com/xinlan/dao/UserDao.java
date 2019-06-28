package com.xinlan.dao;

import com.xinlan.model.User;

public interface UserDao {

    long addUser(User user);

    User queryByUid(long uid);

    void updateUser(User user);
}
