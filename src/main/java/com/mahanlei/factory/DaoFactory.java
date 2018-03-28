package com.mahanlei.factory;

import com.mahanlei.dao.*;
import com.mahanlei.dao.impl.*;

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
public static StadiumDao getStadiumDao(){
    return StadiumDaoImpl.getStadiumDao();
}
public static SeatDao getSeatDao(){
    return SeatDaoImpl.getSeatDao();
}
public static StatisticsDao getStatisticsDao(){
    return StatisticsDaoImpl.getStatisticsDao();
}
public static ApplicationDao getApplicationDao(){
    return ApplicationDaoImpl.getApplicationDao();
}
}
