package com.mahanlei.service.impl;

import com.mahanlei.Util.CalculatePrice;
import com.mahanlei.Util.Message;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.Seat;
import com.mahanlei.model.Ticket;
import com.mahanlei.service.TicketService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private static TicketServiceImpl ticketService = new TicketServiceImpl();

    public static TicketServiceImpl getTicketService() {
        return ticketService;
    }

    public List<Seat> getAllSeat(int showId, int stadiumId) {
        return DaoFactory.getTicketDao().getAllSeat(showId, stadiumId);
    }

    public List<Seat> getSeats(int showId, int stadiumId, int row) {
        return DaoFactory.getTicketDao().getSeats(showId,stadiumId,row);
    }

    public List<Seat> getUnoccupiedSeat(int showId, int stadiumId, int number) {
       List<Seat> seatList= DaoFactory.getTicketDao().getUnoccupiedSeat(showId,stadiumId,number);
       if(seatList!=null){
           return  seatList;
       }else  return  null;
    }

    public Double selectSeat(Seat seat) {
        double price= DaoFactory.getTicketDao().getSeatPrice(seat.getShowId(),seat.getStadiumId(),seat.getSeatRow(),seat.getSeatColumn());
        return price;
    }

    public Message confirmTickets(List<Seat> seatList, String mid) {
        List<Ticket> ticketList = new ArrayList<Ticket>();
        for (int i = 0; i < seatList.size(); i++) {
            Ticket ticket = new Ticket(mid, seatList.get(i).getShowId(), seatList.get(i).getStadiumId(),
                    seatList.get(i).getSeatRow(), seatList.get(i).getSeatColumn());
            ticketList.add(ticket);
        }
        Message addMessage = DaoFactory.getTicketDao().addTicket(ticketList);
        Message updateMessage= DaoFactory.getTicketDao().updateSeat(seatList);
        if(addMessage.equals(Message.SELECT_SUCCESS)&&updateMessage.equals(Message.UPDATE_SUCCESS)){
            return Message.SELECT_SUCCESS;
        }
        else return Message.SELECT_FAILED;
    }

    public double doPay(int tid,int discoutType) {
        Message updateTicketState = DaoFactory.getTicketDao().updateTicketState(tid, 1);
        Ticket ticket = DaoFactory.getTicketDao().getTicketInfo(tid);
        double price = DaoFactory.getTicketDao().getSeatPrice(ticket.getShowId(), ticket.getStadiumId(),
                ticket.getSeatRow(), ticket.getSeatColumn());

      int memRank=  ServiceFactory.getMemberService().getMemberInfo(ticket.getMid()).getRank();
      double disPrice= CalculatePrice.caculatePrice(price,discoutType,memRank);
      Message updatePriceMessage= DaoFactory.getTicketDao().updateTicketPayPrice(tid,disPrice);
         //更新用户账户余额/消费总额
        Message updateBill=DaoFactory.getTicketDao().updateMemBill(ticket.getMid(),disPrice);
        if(updateTicketState.equals(Message.UPDATE_SUCCESS)&&updatePriceMessage.equals(Message.UPDATE_SUCCESS)
                &&updateBill.equals(Message.UPDATE_SUCCESS)){
            return disPrice;

        }
        else return 0;
    }


    public Message refuned(int tid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
       Date currentTime=new Date();// new Date()为获取当前系统时间
      Ticket ticket=DaoFactory.getTicketDao().getTicketInfo(tid);
      int showId=ticket.getShowId();
      String mid=ticket.getMid();
      Date showTime=DaoFactory.getShowDao().getShowInfo(showId).getStartTime();
      int hours= (int) (showTime.getTime()-currentTime.getTime()/1000/60/60);
      double payPrice=DaoFactory.getTicketDao().getTicketInfo(tid).getPayPrice();
      double refunedMoney=0.0;
      if(hours>7*24){
          refunedMoney=payPrice;
      }else if(hours<=7*24&&hours>3*24){
          refunedMoney=payPrice*0.9;
      }else if(hours<=3*24&&hours>24){
          refunedMoney=payPrice*0.85;
      }else if(hours<=24&&hours>2){
          refunedMoney=payPrice*0.5;
      } else refunedMoney=0.0;
      Message updateMemberBill=DaoFactory.getTicketDao().updateMemBill(mid,-refunedMoney);
Message updateTicketState=DaoFactory.getTicketDao().updateTicketState(tid,3);
        if(updateMemberBill.equals(Message.UPDATE_SUCCESS)&&updateTicketState.equals(Message.UPDATE_SUCCESS)){
            return Message.REFUNED_SUCCESS;
        }else return Message.REFUNED_FAILED;

    }

    public int getTicketState(int tid) {
        Ticket ticket = DaoFactory.getTicketDao().getTicketInfo(tid);
        int state=ticket.getState();
        return state;
    }

    public Message checkTicket(int tid) {
        Ticket ticket = DaoFactory.getTicketDao().getTicketInfo(tid);
        double price = ticket.getPayPrice();

        int points = (int) Math.floor(price / 10);
        Message updateTicket=DaoFactory.getTicketDao().updateTicketState(tid,2);
        Message updatePoints = DaoFactory.getTicketDao().updatePoints(ticket.getMid(), points);
        Message updateMemRank=ServiceFactory.getMemberService().updateMemRank(ticket.getMid());
        Message profitToSta = DaoFactory.getTicketDao().updateProfit(String.valueOf(ticket.getStadiumId()), price * 0.6);
        Message profitToMan = DaoFactory.getTicketDao().updateProfit("0000000", price * 0.4);
        if (updateTicket.equals(Message.UPDATE_SUCCESS)&&updateMemRank.equals(Message.UPDATE_SUCCESS)&&
                updatePoints.equals(Message.UPDATE_SUCCESS) && profitToSta.equals(Message.UPDATE_SUCCESS)
                && profitToMan.equals(Message.UPDATE_SUCCESS)) {
            return Message.CHECK_SUCCESS;
        } else return Message.CHECK_FAILED;
    }
}
