package com.xinlan.dao;

import com.xinlan.model.User;

public interface UserDao {

    public long addUser(User user);

    public void updateUser(User user);
}
