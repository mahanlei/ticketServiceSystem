package com.mahanlei.controller;

import com.mahanlei.Util.CalculatePrice;
import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.*;
import com.mahanlei.service.MemberService;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.TicketService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTest {
    public static void  main(String args[]){
//System.out.println((double)DaoFactory.getSeatDao().getSoldSeats(1,1)/(DaoFactory.getStadiumDao().getStadiumInfo(1).getSeatRows()*DaoFactory.getStadiumDao().getStadiumInfo(1).getSeatColumns()));
//        System.out.println(DaoFactory.getTicketDao().getShowTickets(1,1,2));
        ShowService showService=ServiceFactory.getShowService();
        List<ShowInfo> shows=showService.getStaShow("0000001");
        System.out.println(shows.size());
        System.out.println(shows.get(1).getShowId());
    }

}
