package com.mahanlei.controller;

import com.mahanlei.Util.CalculatePrice;
import com.mahanlei.Util.TransDataType;
import com.mahanlei.dao.StatisticsDao;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.*;
import com.mahanlei.service.MemberService;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.TicketService;
import com.sun.javafx.animation.TickCalculation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTest {
    public static void  main(String args[]){
//System.out.println((double)DaoFactory.getSeatDao().getSoldSeats(1,1)/(DaoFactory.getStadiumDao().getStadiumInfo(1).getSeatRows()*DaoFactory.getStadiumDao().getStadiumInfo(1).getSeatColumns()));
//        System.out.println(DaoFactory.getTicketDao().getShowTickets(1,1,2));
//        ShowService showService=ServiceFactory.getShowService();
//        List<ShowInfo> shows=showService.getStaShow("0000001");
//        System.out.println(shows.size());
//        System.out.println(shows.get(1).getShowId());
//       List<TicketCount> ticketCounts=ServiceFactory.getStaticsService().getTicketCount();
//       int[]numbers=ServiceFactory.getStaticsService().countMemberByAge();
////        List<TicketCount> ticketCounts1=DaoFactory.getStatisticsDao().getTicketCount();
//        for(int i=0;i<numbers.length;i++){
//            System.out.println(numbers[i]);
//
//        }
        System.out.println(DaoFactory.getMemberDao().activeMember("ab7fc43482994293baee07faa912bf64"));
//        System.out.println(ticketCounts1.get(1).getTime()+ticketCounts1.get(1).getNumber());

    }

}
