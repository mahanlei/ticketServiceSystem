package com.mahanlei.factory;

import com.mahanlei.dao.UserDao;
import com.mahanlei.dao.impl.UserDaoImpl;

public class DaoFactory {
public static  UserDao getUserDao(){
   return UserDaoImpl.getInstance();
}

}
