package com.mahanlei.factory;

import com.mahanlei.dao.MemberDao;
import com.mahanlei.dao.UserDao;
import com.mahanlei.dao.impl.MemberDaoImpl;
import com.mahanlei.dao.impl.UserDaoImpl;

public class DaoFactory {
public static  UserDao getUserDao(){
   return UserDaoImpl.getInstance();
}
public static MemberDao getMemberDao(){return MemberDaoImpl.getInstance();}
}
