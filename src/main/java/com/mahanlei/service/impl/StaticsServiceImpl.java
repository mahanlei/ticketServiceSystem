package com.mahanlei.service.impl;

import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.model.SoldTicket;
import com.mahanlei.model.StadiumInfo;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.StadiumService;
import com.mahanlei.service.StaticsService;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
}
