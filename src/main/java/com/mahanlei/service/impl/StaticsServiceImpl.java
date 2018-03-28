package com.mahanlei.service.impl;

import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.*;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.StadiumService;
import com.mahanlei.service.StaticsService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaticsServiceImpl  implements StaticsService{
    private static StaticsService staticsService=new StaticsServiceImpl();

    public static StaticsService getStaticsService() {
        return staticsService;
    }

    @Override
    public List<SoldTicket> getSoldTicketRate(int stadiumId) {
        ShowService showService= ServiceFactory.getShowService();
        StadiumService stadiumService=ServiceFactory.getStadiumService();

        StadiumInfo stadiumInfo=stadiumService.getStadiumInfo(stadiumId);
        int seats=stadiumInfo.getSeatColumns()*stadiumInfo.getSeatRows();
        List<ShowInfo> showInfoList=showService.getStaShow(TransDataType.intToString(stadiumId));
        List<SoldTicket> rates=new ArrayList<>();
        for(int i=0;i<showInfoList.size();i++){
            int showId=showInfoList.get(i).getShowId();
            int soldTickets= ServiceFactory.getSeatService().getSoldSeats(showId,stadiumId);
            double rate=(double)soldTickets/seats;
            DecimalFormat df = new DecimalFormat("0.0000");
           double rate2=Double.valueOf(df.format(rate));
            SoldTicket soldTicket=new SoldTicket(showId,rate2);
            rates.add(soldTicket);

        }
        return rates;
    }

    @Override
    public List<SoldTicket> getAttendenceRate(int stadiumId) {
        ShowService showService= ServiceFactory.getShowService();

        List<SoldTicket> rates=new ArrayList<>();
        List<ShowInfo> showInfoList=showService.getStaShow(TransDataType.intToString(stadiumId));
        for(int i=0;i<showInfoList.size();i++){
            int showId=showInfoList.get(i).getShowId();
            int consumedTickets= ServiceFactory.getTicketService().getShowTickets(showId,stadiumId,2);
            int orderedTickets=ServiceFactory.getTicketService().getShowTickets(showId,stadiumId,1);
            double rate=(double)consumedTickets/(consumedTickets+orderedTickets);
            DecimalFormat df = new DecimalFormat("0.0000");
            double rate2=Double.valueOf(df.format(rate));
            SoldTicket soldTicket=new SoldTicket(showId,rate2);
            rates.add(soldTicket);
        }
        return rates;
    }

    @Override
    public double getProfit(String id) {
        return DaoFactory.getStatisticsDao().getProfit(id);
    }

    @Override
    public List<TicketCount> getTicketCount() {
        Date currentTime=new Date();
        Date now=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTimeStr=dateFormat.format(currentTime);
        List<TicketCount> result=new ArrayList<>();
        try {
             now = dateFormat.parse(currentTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<TicketCount> ticketCounts=DaoFactory.getStatisticsDao().getTicketCount();
        int j=0;
        for(int i=0;i<ticketCounts.size()||j<7;){
            j++;
            if(i<ticketCounts.size()){
    try {
        Date oldDate = dateFormat.parse(ticketCounts.get(i).getTime());
        int days = (int) ((now.getTime() - oldDate.getTime()) / (1000 * 3600 * 24));
        if (days == 7 - j) {
            result.add(ticketCounts.get(i));
            i++;
        } else {
            result.add(new TicketCount(dateFormat.format(new Date(now.getTime() - (7 - j) * (1000 * 3600 * 24))), 0));
        }



    } catch (ParseException e) {
        e.printStackTrace();
    }
            }else{
                result.add(new TicketCount(dateFormat.format(new Date(now.getTime() - (7 - j) * (1000 * 3600 * 24))), 0));

            }
        }
        return result;
    }

    @Override
    public List<TicketPrice> getTicketPrice() {
        Date currentTime=new Date();
        Date now=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTimeStr=dateFormat.format(currentTime);
        List<TicketPrice> result=new ArrayList<>();
        try {
            now = dateFormat.parse(currentTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<TicketPrice> ticketCounts=DaoFactory.getStatisticsDao().getTicketPrice();
        int j=0;
        for(int i=0;i<ticketCounts.size()||j<7;){
            j++;
            if(i<ticketCounts.size()){
                try {
                    Date oldDate = dateFormat.parse(ticketCounts.get(i).getTime());
                    int days = (int) ((now.getTime() - oldDate.getTime()) / (1000 * 3600 * 24));
                    if (days == 7 - j) {
                        result.add(ticketCounts.get(i));
                        i++;
                    } else {
                        result.add(new TicketPrice(0.0,dateFormat.format(new Date(now.getTime() - (7 - j) * (1000 * 3600 * 24)))));
                    }



                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                result.add(new TicketPrice(0.0,dateFormat.format(new Date(now.getTime() - (7 - j) * (1000 * 3600 * 24)))));

            }
        }
        return result;
    }

    @Override
    public List<StadiumShows> getStadiumShows() {
        List<StadiumShows> result=new ArrayList<>();
        List<Integer> staIds=DaoFactory.getStatisticsDao().getAllStadiumId();
        for(int i=0;i<staIds.size();i++){
            List<ShowInfo> showInfoList=ServiceFactory.getShowService().getStaShow(TransDataType.intToString(staIds.get(i)));
            int size=showInfoList.size();
            StadiumShows stadiumShows=new StadiumShows(staIds.get(i),size);
            result.add(stadiumShows);
        }
        return result;


    }

    @Override
    public int[] countMemberByAge() {
        return DaoFactory.getStatisticsDao().countMemberByAge();
    }
}
