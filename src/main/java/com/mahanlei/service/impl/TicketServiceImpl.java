package com.mahanlei.service.impl;

import com.mahanlei.Util.CalculatePrice;
import com.mahanlei.Util.Message;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.*;
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date currentTime=new Date();// new Date()为获取当前系统时间
        List<Ticket> ticketList = new ArrayList<Ticket>();
        for (int i = 0; i < seatList.size(); i++) {
            Ticket ticket = new Ticket(mid, seatList.get(i).getShowId(), seatList.get(i).getStadiumId(),
                    seatList.get(i).getSeatRow(), seatList.get(i).getSeatColumn(),currentTime,seatList.get(i).getPrice());
            ticketList.add(ticket);
        }
        Message addMessage = DaoFactory.getTicketDao().addTicket(ticketList);
        Message updateMessage= DaoFactory.getTicketDao().updateSeat(seatList);
        if(addMessage.equals(Message.SELECT_SUCCESS)&&updateMessage.equals(Message.UPDATE_SUCCESS)){
            return Message.SELECT_SUCCESS;
        }
        else return Message.SELECT_FAILED;
    }

    public Message doPay(List<Integer> ticketList,double totalPayPrice) {
        List<Message>  updateTicketStateList=new ArrayList<Message>();
        for(int i=0;i<ticketList.size();i++){
            Message updateTicketState = DaoFactory.getTicketDao().updateTicketState(ticketList.get(i), 1);
            updateTicketStateList.add(updateTicketState);
            //更新用户账户余额/消费总额,账单的状态
        }
        boolean isSuccess=true;
        for(int i=0;i<updateTicketStateList.size();i++){
            if(updateTicketStateList.get(i).equals(Message.UPDATE_FAILED)){
                isSuccess=false;
                break;
            }
        }
        Message updateBill=DaoFactory.getTicketDao().updateMemBill(DaoFactory.getTicketDao().getTicketInfo(ticketList.get(0)).getMid(),totalPayPrice);

        if(isSuccess&&updateBill.equals(Message.UPDATE_SUCCESS)){
            return Message.PAY_SUCCESS;

        }
        else return Message.PAY_FAILED;
    }

    public Message closeDeal(List<Integer> ticketList) {
        Message message=Message.UPDATE_SUCCESS;
        List<Seat> seatList=new ArrayList<Seat>();
        for(int i=0;i<ticketList.size();i++){
            Ticket ticket=DaoFactory.getTicketDao().getTicketInfo(ticketList.get(i));
            int showId=ticket.getShowId();
            int stadiumId=ticket.getStadiumId();
            int seatRow=ticket.getSeatRow();
            int seatColumn=ticket.getSeatColumn();
            Seat seat=new Seat(showId,stadiumId,seatRow,seatColumn,0);
            seatList.add(seat);
            Message message2=DaoFactory.getTicketDao().updateSeat(seatList);
         Message message1=DaoFactory.getTicketDao().updateTicketState(ticketList.get(i),4);
         if(message1.equals(Message.UPDATE_FAILED)&&message2.equals(Message.UPDATE_FAILED)){
           message=Message.UPDATE_FAILED;
          }
        }
        return message;
    }


    public Message refuned(int tid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
       Date currentTime=new Date();// new Date()为获取当前系统时间
      Ticket ticket=DaoFactory.getTicketDao().getTicketInfo(tid);
      int showId=ticket.getShowId();
      String mid=ticket.getMid();
      Date showTime=DaoFactory.getShowDao().getShowInfo(showId).getStartTime();
      int hours= (int) ((showTime.getTime()-currentTime.getTime())/1000/60/60);
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
        System.out.println("退款时间差"+hours);
      System.out.println("退款"+refunedMoney);
      Message updateMemberBill=DaoFactory.getTicketDao().updateMemBill(mid,-refunedMoney);
      Message updateTicketState=DaoFactory.getTicketDao().updateTicketState(tid,3);
      Message updateTicketRefundedTime=DaoFactory.getTicketDao().updateTicketRefunedTime(tid,currentTime);
      Message updateSeatState=DaoFactory.getTicketDao().updateSeatState(new Seat(showId,ticket.getStadiumId(),ticket.getSeatRow(),ticket.getSeatColumn(),0));
        if(updateMemberBill.equals(Message.UPDATE_SUCCESS)
                &&updateTicketRefundedTime.equals(Message.UPDATE_SUCCESS)
                &&updateSeatState.equals(Message.UPDATE_SUCCESS)
                &&updateTicketState.equals(Message.UPDATE_SUCCESS)){
            return Message.REFUNED_SUCCESS;
        }else return Message.REFUNED_FAILED;

    }

    public Ticket getTicketInfo(int tid) {
        Ticket ticket = DaoFactory.getTicketDao().getTicketInfo(tid);
        return ticket;
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
        if (updateTicket.equals(Message.UPDATE_SUCCESS)&&
                updatePoints.equals(Message.UPDATE_SUCCESS) && profitToSta.equals(Message.UPDATE_SUCCESS)
                && profitToMan.equals(Message.UPDATE_SUCCESS)) {
            return Message.CHECK_SUCCESS;
        } else return Message.CHECK_FAILED;
    }

    public List<Integer> getTid(String mid, int showId, int stadiumId,int state) {
        return DaoFactory.getTicketDao().getTid(mid,showId,stadiumId,state);
    }

    public int getATid(String mid, int showId, int stadiumId, int seatRow, int seatColumn) {
        return DaoFactory.getTicketDao().getATid(mid,showId,stadiumId,seatRow,seatColumn);
    }

    public List<TicketInfoBrief> getMyTicketInfo(String mid, int state) {

      List<Integer> tidList=DaoFactory.getTicketDao().getMyTicketsId(mid,state);
//      System.out.println(tidList.size());
      List<TicketInfoBrief> ticketInfoBriefList=new ArrayList<TicketInfoBrief>();
      if(tidList.size()!=0){
          for(int i=0;i<tidList.size();i++){
              Ticket ticket=DaoFactory.getTicketDao().getTicketInfo(tidList.get(i));
              int showId=ticket.getShowId();
              ShowInfo showInfo=DaoFactory.getShowDao().getShowInfo(showId);
              TicketInfoBrief ticketInfoBrief=new TicketInfoBrief(ticket.getTid(),showInfo.getName(),showInfo.getStaName(),
                      showInfo.getPicture(),ticket.getSeatRow(),ticket.getSeatColumn(),
                      ticket.getCreatedTime(),ticket.getRefunedTime(),ticket.getPayPrice());
//              System.out.println(ticketInfoBrief.getCreatedTime());
              ticketInfoBriefList.add(ticketInfoBrief);
          }
      }
      return ticketInfoBriefList;
    }

    public double getDisPrice(int tid, int discountType) {

        Ticket ticket = DaoFactory.getTicketDao().getTicketInfo(tid);
        double price = DaoFactory.getTicketDao().getSeatPrice(ticket.getShowId(), ticket.getStadiumId(),
                ticket.getSeatRow(), ticket.getSeatColumn());
//        System.out.println(price);
        int memRank=0;
        if(ServiceFactory.getMemberService().getMemberInfo(ticket.getMid())!=null){
            memRank=  ServiceFactory.getMemberService().getMemberInfo(ticket.getMid()).getRank();
        }
        if(discountType==2){
            DiscountCoupon discountCoupon=new DiscountCoupon(ticket.getMid(),1,0,0,0);
            Message useDis=ServiceFactory.getMemberService().useDis(discountCoupon);

        }else if(discountType==3){
            DiscountCoupon discountCoupon=new DiscountCoupon(ticket.getMid(),0,1,0,0);
            Message useDis=ServiceFactory.getMemberService().useDis(discountCoupon);


        }else if(discountType==4){
            DiscountCoupon discountCoupon=new DiscountCoupon(ticket.getMid(),0,0,1,0);
            Message useDis=ServiceFactory.getMemberService().useDis(discountCoupon);

        }else if(discountType==5){
            DiscountCoupon discountCoupon=new DiscountCoupon(ticket.getMid(),0,0,0,1);
            Message useDis=ServiceFactory.getMemberService().useDis(discountCoupon);

        }
        double disPrice = CalculatePrice.calculatePrice(price, discountType, memRank);
//        System.out.println(disPrice);
            Message updatePriceMessage = DaoFactory.getTicketDao().updateTicketPayPrice(tid, disPrice);
            if (updatePriceMessage.equals(Message.UPDATE_SUCCESS)) {
                return disPrice;
            } else return 0;
        }

}
