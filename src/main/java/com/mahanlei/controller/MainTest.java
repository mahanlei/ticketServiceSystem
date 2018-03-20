package com.mahanlei.controller;

import com.mahanlei.factory.DaoFactory;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.*;
import com.mahanlei.service.MemberService;
import com.mahanlei.service.TicketService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainTest {
    public static void  main(String args[]){
        String mid="ma123";
//        double price=3.3;
//        System.out.println(-price);
//        UserService userService= ServiceFactory.getUserService();
//        MemberService memberService=ServiceFactory.getMemberService();
//       System.out.print( memberService.doRegister("mmmm","111",123,"123"));
//        MailUtil mailUtil=new MailUtil("13585141983@163.com","8");
//        new Thread(new MailUtil("13585141983@163.com", "8")).start();
//        System.out.println(memberService.getDisInfo(mid).getCount4());
//        System.out.println(memberService.getDisInfo(mid).getCount3());
//        System.out.println(memberService.exchangeDis(new DiscountCoupon(mid, 0,1,1,2,0,1,0,3)));
//        System.out.println(memberService.getDisInfo(mid).getCount3());
//        System.out.println(memberService.useDis(new DiscountCoupon(mid, 0,1,0,2,0,1,1,3)));
//        System.out.println(memberService.getDisInfo(mid).getCount4());

//        ShowService showService=ServiceFactory.getShowService();
//        List<ShowInfoBrief> list=showService.getAllShowInfo();
//        for(int i=0;i<list.size();i++){
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("showId",list.get(i).getShowId());
//            jsonObject.put("name",list.get(i).getName());
//            jsonObject.put("type", TransDataType.showTypeToInt(list.get(i).getType()));
//            jsonObject.put("picture",list.get(i).getPicture());
//            System.out.println(jsonObject);
//        }
//        ShowInfo showInfo=showService.getShowInfo(1);
//        System.out.println(new Timestamp(showInfo.getStartTime().getTime()));
//        System.out.println(showInfo.getStaName());
//        System.out.println(showInfo.getAddress());
//        System.out.println(showInfo.getName());
//        System.out.println(TransDataType.showTypeToInt(showInfo.getType()));
//        TicketService ticketService=ServiceFactory.getTicketService();
//        List<Seat> seatList=ticketService.getAllSeat(1,1);
//        System.out.println(seatList.get(0).getPrice());
//        Seat seat=new Seat(1,1,1,1,1);
//        List<Seat> seatList=new ArrayList<Seat>();
//        seatList.add(seat);
////        System.out.println(ticketService.selectSeat(seatList,mid));
//System.out.println(ticketService.refuned(1));
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
TicketService ticketService=ServiceFactory.getTicketService();
//List<TicketInfoBrief> seatList=ticketService.getMyTicketInfo("ma123",2);

        List<Seat> seatList=ticketService.getUnoccupiedSeat(1,1,7);
        System.out.println(seatList==null);
////System.out.println(seatList.size());
//System.out.println(DaoFactory.getTicketDao().getTicketInfo(DaoFactory.getTicketDao().getMyTicketsId("ma123",1).get(0)).getCreatedTime());
//        List<Integer> tidList=DaoFactory.getTicketDao().getMyTicketsId(mid,1);
//        System.out.println(tidList.size());
//        List<TicketInfoBrief> ticketInfoBriefList=new ArrayList<TicketInfoBrief>();
//        if(tidList.size()!=0){
//            for(int i=0;i<tidList.size();i++){
//                Ticket ticket=DaoFactory.getTicketDao().getTicketInfo(tidList.get(i));
//                int showId=ticket.getShowId();
//                ShowInfo showInfo=DaoFactory.getShowDao().getShowInfo(showId);
//                TicketInfoBrief ticketInfoBrief=new TicketInfoBrief(showInfo.getName(),showInfo.getStaName(),
//                        showInfo.getPicture(),ticket.getSeatRow(),ticket.getSeatColumn(),
//                        ticket.getCreatedTime(),ticket.getRefunedTime(),ticket.getPayPrice());
//                System.out.println(ticketInfoBrief.getCreatedTime());
//                ticketInfoBriefList.add(ticketInfoBrief);
//            }
//        }
    }

}
