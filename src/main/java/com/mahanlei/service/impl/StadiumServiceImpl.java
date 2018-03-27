package com.mahanlei.service.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.DaoFactory;
import com.mahanlei.model.Application;
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

    public Message addStadium(StadiumInfo stadiumInfo,String password) {

        Message message1= DaoFactory.getStadiumDao().addStadium(stadiumInfo);
        int staId=DaoFactory.getStadiumDao().getStaId(stadiumInfo.getStaName(),stadiumInfo.getAddress());
        Application application=new Application(staId,0,1,stadiumInfo.getStaName(),
                stadiumInfo.getAddress(),stadiumInfo.getSeatRows(),stadiumInfo.getSeatColumns());
       Message message=DaoFactory.getStadiumDao().addApplication(application);
Message message2=DaoFactory.getStadiumDao().addStaPass(staId,password);
Message message3=DaoFactory.getStadiumDao().addStaProfit(staId);
        if(message.equals(Message.APPLY_SUCCESS)&&message1.equals(Message.UPDATE_SUCCESS)
                &&message2.equals(Message.UPDATE_SUCCESS)&&message3.equals(Message.UPDATE_SUCCESS)){
            return Message.REGISTER_SUCCESS;
        }else return Message.REGISTER_FAILED;
    }

    public Message activeStadium(int stadiumId) {
        return DaoFactory.getStadiumDao().activeStadium(stadiumId);
    }

    public Message updateStaInfo(int stadiumId, String staName) {
        Application application=new Application(stadiumId,0,2,staName);
        Message message=DaoFactory.getStadiumDao().addApplication(application);
       Message message1= DaoFactory.getStadiumDao().updateStaInfo(stadiumId,staName);
        if(message.equals(Message.APPLY_SUCCESS)&&message1.equals(Message.UPDATE_SUCCESS)){
            return Message.UPDATE_SUCCESS;
        }else return Message.UPDATE_FAILED;
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
