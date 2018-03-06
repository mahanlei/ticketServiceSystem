package com.mahanlei.service.impl;

import com.mahanlei.dao.UserDao;
import com.mahanlei.dao.impl.UserDaoImpl;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.service.UserService;
import org.springframework.stereotype.Repository;


public class UserServiceImpl implements UserService {
private  static UserServiceImpl userService=new UserServiceImpl();
public static UserServiceImpl getInstance(){
    return  userService;
}
    public String getPassw(String uid) {
        UserDao userDao= DaoFactory.getUserDao();
       return userDao.findPassword(uid);
    }
}
