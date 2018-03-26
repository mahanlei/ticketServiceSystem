package com.mahanlei.controller.memberController;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.Seat;
import com.mahanlei.model.Ticket;
import com.mahanlei.service.TicketService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/buyTicket")
public class BuyTicketController {
    TicketService ticketService = ServiceFactory.getTicketService();

    @RequestMapping(value = "/confirmSeats", method = RequestMethod.POST)
    public JSONObject confirmSeats(@RequestParam("seats") String seats) {
        JSONObject jsonObject = new JSONObject();
        JSONArray seatList = JSONArray.fromObject(seats);

        String mid = seatList.getJSONObject(0).getString("mid");
        List<Seat> seatList1 = new ArrayList<Seat>();
        for (int i = 0; i < seatList.size(); i++) {
            JSONObject seat = seatList.getJSONObject(i);
//            System.out.println(seat.getInt("showId"));
            Seat seatObject = new Seat(seat.getInt("showId"), seat.getInt("stadiumId"), seat.getInt("seatRow"), seat.getInt("seatColumn"), seat.getDouble("price"),1);
            seatList1.add(seatObject);
        }
//      修改座位信息为已售出，生成订单
        Message message = ticketService.confirmTickets(seatList1, mid);
//        System.out.println(message);
        if (message.equals(Message.SELECT_SUCCESS)) {
            jsonObject.put("code", "200");
            jsonObject.put("msg", "订单待支付");
        } else {
            jsonObject.put("code", "500");
            jsonObject.put("msg", "订单生成失败");
        }
        return jsonObject;
    }
@RequestMapping(value = "/getUnoccupiedSeats",method = RequestMethod.POST)
    public JSONObject getUnoccupiedSeats(@RequestParam("mid") String mid,
                                         @RequestParam("showId") int showId,
                                        @RequestParam("stadiumId") int stadiumId,
                                        @RequestParam("number") int number){
        JSONObject jsonObject=new JSONObject();
    Message message =null;
            List<Seat> seatList=  ticketService.getUnoccupiedSeat(showId,stadiumId,number);
      if(seatList!=null){
          for(int i=0;i<seatList.size();i++){
              seatList.get(i).setState(1);
          }
          message = ticketService.confirmTickets(seatList, mid);
          if(message.equals(Message.SELECT_SUCCESS)){
              jsonObject.put("code","200");
              jsonObject.put("msg","配票成功");
          }else {
              jsonObject.put("code","500");
              jsonObject.put("msg","配票失败,请再次尝试");
          }
      }
      else {
          jsonObject.put("code","500");
          jsonObject.put("msg","配票失败，余票不足");
      }
      return jsonObject;
}
    @RequestMapping(value = "/getPayPrice", method = RequestMethod.POST)
    public JSONObject getPayPrice(@RequestParam("mid") String mid,
                                  @RequestParam("showId") int showId,
                                  @RequestParam("stadiumId") int stadiumId,
                                  @RequestParam("discountType") int discountType) {
//        System.out.println(mid+","+showId+","+stadiumId+","+discountType);
        List<Integer> tidList = ticketService.getTid(mid, showId, stadiumId,0);

        List<Seat> seatList=new ArrayList<Seat>();
        double totalPayPrice = 0.0;
        for (int i = 0; i < tidList.size(); i++) {
            totalPayPrice =totalPayPrice+ ticketService.getDisPrice(tidList.get(i), discountType);
            Ticket ticket=ticketService.getTicketInfo(tidList.get(i)) ;
            Seat seat=new Seat(ticket.getShowId(),ticket.getStadiumId(),ticket.getSeatRow(),ticket.getSeatColumn(),ticket.getPayPrice(),ticket.getState());
            seatList.add(seat);
        }
        JSONObject jsonObject = new JSONObject();
//        System.out.println(totalPayPrice);
        DecimalFormat df = new DecimalFormat("0.00");
        jsonObject.put("totalPayPrice", Double.valueOf(df.format(totalPayPrice)));
        jsonObject.put("seats",seatList);
        return jsonObject;
    }

   @RequestMapping(value = "/getCreatedTime",method = RequestMethod.POST)
    public JSONObject getCreatedTime(@RequestParam("mid") String mid,
                                  @RequestParam("showId") int showId,
                                  @RequestParam("stadiumId") int stadiumId) {
        List<Integer> tidList = ticketService.getTid(mid, showId, stadiumId,0);
//        System.out.println(tidList.get(0));
        Date createdTime=ticketService.getTicketInfo(tidList.get(0)).getCreatedTime();

       System.out.println(createdTime.getTime());
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdTime);
        System.out.println(dateStr);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("createdTime",dateStr);
        return jsonObject;
    }

    @RequestMapping(value = "/doPay", method = RequestMethod.POST)
    public JSONObject doPay(@RequestParam("mid") String mid,
                            @RequestParam("showId") int showId,
                            @RequestParam("stadiumId") int stadiumId,
                            @RequestParam("totalPayPrice") double totalPayPrice) {
        List<Integer> tidList = ticketService.getTid(mid, showId, stadiumId,0);

        Message message = ticketService.doPay(tidList, totalPayPrice);
        JSONObject jsonObject = new JSONObject();
        if (message.equals(Message.PAY_SUCCESS)) {
            jsonObject.put("code", "200");
            jsonObject.put("msg", "支付成功");
        } else {
            jsonObject.put("code", "500");
            jsonObject.put("msg", "支付失败");
        }
        return jsonObject;
    }
    @RequestMapping(value="/checkTicketState",method = RequestMethod.POST)
    public JSONObject checkTicketState(@RequestParam("mid") String mid,
                                       @RequestParam("showId") int showId,
                                       @RequestParam("stadiumId") int stadiumId){
        JSONObject jsonObject=new JSONObject();
        List<Integer> tidList = ticketService.getTid(mid, showId, stadiumId,0);
        if(tidList.size()==0){
            jsonObject.put("state","1");
            jsonObject.put("msg","已支付");
        }else {
            jsonObject.put("state","0");
            jsonObject.put("msg","未支付");
        }
return jsonObject;
    }
    @RequestMapping(value="/closeDeal",method = RequestMethod.POST)
    public JSONObject closeDeals(@RequestParam("mid") String mid,
                                 @RequestParam("showId") int showId,
                                 @RequestParam("stadiumId") int stadiumId){
        List<Integer> tidList = ticketService.getTid(mid, showId, stadiumId,0);
        Message message=ticketService.closeDeal(tidList);
        JSONObject jsonObject=new JSONObject();
        if(message.equals(message.equals(Message.UPDATE_SUCCESS))){
            jsonObject.put("code","200");
            jsonObject.put("msg","已关闭交易");

        }else {
            jsonObject.put("code","500");
            jsonObject.put("msg","关闭交易失败");
        }
        return jsonObject;

    }
}
