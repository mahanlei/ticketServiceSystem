package com.mahanlei.service.impl;

import com.mahanlei.Util.CalculatePrice;
import com.mahanlei.Util.Message;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.Seat;
import com.mahanlei.service.SeatService;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.StadiumService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeatServiceImpl implements SeatService {
    private static SeatServiceImpl seatService=new SeatServiceImpl();

    public static SeatServiceImpl getSeatService() {
        return seatService;
    }

    @Override
    public Message addSeats(String showName, int stadiumId, Date startTime,double price) {
        ShowService showService= ServiceFactory.getShowService();
        StadiumService stadiumService=ServiceFactory.getStadiumService();
        int showId=showService.getShowId(showName,stadiumId,startTime);
        List<Seat> seatList=new ArrayList<>();
        int seatRow=stadiumService.getStadiumInfo(stadiumId).getSeatRows();
        int seatColumn=stadiumService.getStadiumInfo(stadiumId).getSeatColumns();
        for(int i=1;i<=seatRow;i++){
            for(int j=1;j<=seatColumn;j++){
double newPrice= CalculatePrice.getSeatPrice(seatRow,i,seatColumn,j,price);
Seat seat=new Seat(showId,stadiumId,i,j,newPrice,0);
seatList.add(seat);
            }
        }
        Message message=DaoFactory.getSeatDao().addSeats(seatList);
        if(message.equals(Message.UPDATE_SUCCESS)){
            return Message.UPDATE_SUCCESS;
        }else{
            return Message.UPDATE_FAILED;
        }
    }
}
