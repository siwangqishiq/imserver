package com.xinlan.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class BaseService {
    protected ReadWriteLock mlock;
    protected Lock mReadLock;
    protected Lock mWriteLock;

    public BaseService(){
        mlock = new ReentrantReadWriteLock();
        mReadLock = mlock.readLock();
        mWriteLock = mlock.writeLock();
    }

}//end class
