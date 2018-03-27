package com.mahanlei.factory;

import com.mahanlei.service.*;
import com.mahanlei.service.impl.*;

public class ServiceFactory {
    public  static UserService getUserService(){
     return    UserServiceImpl.getInstance();
    }
    public static MemberService getMemberService(){
        return MemberServiceImpl.getInstance();
    }
    public static ShowService getShowService(){
        return ShowServiceImpl.getInstance();
    }
    public static TicketService getTicketService(){
        return TicketServiceImpl.getTicketService();
    }
    public static StadiumService getStadiumService(){
        return StadiumServiceImpl.getStadiumService();
    }
    public static SeatService getSeatService(){
        return SeatServiceImpl.getSeatService();
    }
    public static StaticsService getStaticsService(){
        return StaticsServiceImpl.getStaticsService();
    }
}
