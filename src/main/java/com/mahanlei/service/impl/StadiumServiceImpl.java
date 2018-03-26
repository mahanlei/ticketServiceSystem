package com.mahanlei.service.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.model.Seat;
import com.mahanlei.model.StadiumInfo;
import com.mahanlei.model.Ticket;
import com.mahanlei.service.StadiumService;

import java.util.ArrayList;
import java.util.List;

public class StadiumServiceImpl  implements StadiumService{
    private static  StadiumServiceImpl stadiumService=new StadiumServiceImpl();

    public static StadiumServiceImpl getStadiumService() {
        return stadiumService;
    }

    public StadiumInfo getStadiumInfo(int stadiumId) {
        return DaoFactory.getStadiumDao().getStadiumInfo(stadiumId);
    }

    public Message addStadium(StadiumInfo stadiumInfo) {
       return DaoFactory.getStadiumDao().addStadium(stadiumInfo);
    }

    public Message activeStadium(int stadiumId) {
        return DaoFactory.getStadiumDao().activeStadium(stadiumId);
    }

    public Message updateStaInfo(int stadiumId, String staName) {
        return DaoFactory.getStadiumDao().updateStaInfo(stadiumId,staName);
    }

    public Message doPay(List<Integer> ticketList) {
        List<Message>  updateTicketStateList=new ArrayList<Message>();
        for(int i=0;i<ticketList.size();i++){
            Message updateTicketState = DaoFactory.getTicketDao().updateTicketState(ticketList.get(i), 1);
            updateTicketStateList.add(updateTicketState);
        }
        boolean isSuccess=true;
        for(int i=0;i<updateTicketStateList.size();i++){
            if(updateTicketStateList.get(i).equals(Message.UPDATE_FAILED)){
                isSuccess=false;
                break;
            }
        }

        if(isSuccess){
            return Message.PAY_SUCCESS;
        }
        else return Message.PAY_FAILED;
    }

    public Message doUnPay(List<Integer> ticketList) {
        List<Message>  updateTicketStateList=new ArrayList<Message>();
        List<Message>  updateSeatStateList=new ArrayList<Message>();
        for(int i=0;i<ticketList.size();i++){
            Ticket ticket = DaoFactory.getTicketDao().getTicketInfo(ticketList.get(i));
          Message message1=  DaoFactory.getTicketDao().updateSeatState(new Seat(ticket.getShowId(),ticket.getStadiumId(),ticket.getSeatRow(),ticket.getSeatColumn(),0 ));
         Message message2=DaoFactory.getTicketDao().updateTicketState(ticket.getTid(),4);
updateSeatStateList.add(message1);
updateTicketStateList.add(message2);
        }
        boolean isSuccess1=true;
        for(int i=0;i<updateSeatStateList.size();i++) {
            if (updateTicketStateList.get(i).equals(Message.UPDATE_FAILED)) {
                isSuccess1 = false;
                break;
            }
        }
            boolean isSuccess2=true;
            for(int i=0;i<updateSeatStateList.size();i++){
                if(updateTicketStateList.get(i).equals(Message.UPDATE_FAILED)){
                    isSuccess2=false;
                    break;
                }
        }
        if(isSuccess1&&isSuccess2){
            return Message.UNPAY_SUCCESS;
        }
        else return Message.UNPAY_FAILED;
    }
}
