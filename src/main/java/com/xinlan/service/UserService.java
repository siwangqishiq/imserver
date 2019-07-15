package com.xinlan.service;

import com.xinlan.dao.DaoFactory;
import com.xinlan.dao.UserDao;
import com.xinlan.exception.CommonException;
import com.xinlan.model.User;

import java.util.Date;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserService extends BaseService {
    private UserDao userDao;

    public UserService() {
        userDao = DaoFactory.genUserDao();
    }

    public long addUser(User user) {
//        if(mWriteLock.tryLock()){
//            try{
//                return userDao.addUser(user);
//            }finally {
//                mWriteLock.unlock();
//            }
//        }else{
//            System.out.println("获取写锁失败");
//            return -1;
//        }
        mWriteLock.lock();
        try{
            return userDao.addUser(user);
        }finally {
            mWriteLock.unlock();
        }
    }

    public User addUser(String account, String pwd, String avatar, int male) throws CommonException {
        User u = queryUserByAccount(account);
        if (u != null) {
            throw new CommonException("此用户名已存在");
        }

        User user = new User();
        user.setAccount(account);
        user.setPwd(pwd);
        user.setAvatar(avatar);
        user.setState(male);
        user.setCreateTime(new Date().getTime());
        user.setUpdateTime(new Date().getTime());

        long uid = addUser(user);
        user.setUid(uid);
        user.setPwd(null);
        return user;
    }

    public User queryUser(long uid) {
        mReadLock.lock();
        try {
            return userDao.queryByUid(uid);
        } finally {
            mReadLock.unlock();
        }
    }

    public User queryUserByAccount(String account) {
        mReadLock.lock();
        try {
            List<User> list = userDao.queryByAccount(account);
            if (list != null && list.size() > 0)
                return list.get(0);

            return null;
        } finally {
            mReadLock.unlock();
        }
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}//end class
