package com.mahanlei.factory;

import com.mahanlei.dao.MemberDao;
import com.mahanlei.dao.ShowDao;
import com.mahanlei.dao.TicketDao;
import com.mahanlei.dao.UserDao;
import com.mahanlei.dao.impl.MemberDaoImpl;
import com.mahanlei.dao.impl.ShowDaoImpl;
import com.mahanlei.dao.impl.TicketDaoImpl;
import com.mahanlei.dao.impl.UserDaoImpl;

public class DaoFactory {
public static  UserDao getUserDao(){
   return UserDaoImpl.getInstance();
}
public static MemberDao getMemberDao(){return MemberDaoImpl.getInstance();}
public static ShowDao getShowDao(){
   return ShowDaoImpl.getInstance();
}
public static TicketDao getTicketDao(){
   return TicketDaoImpl.getTicketDao();
}
}
