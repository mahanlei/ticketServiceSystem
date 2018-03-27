package com.mahanlei.controller.stadiumController;

import com.mahanlei.Util.Message;
import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.StaTicket;
import com.mahanlei.model.Ticket;
import com.mahanlei.service.StadiumService;
import com.mahanlei.service.TicketService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/stadium")
public class StaTicketController {
    TicketService ticketService= ServiceFactory.getTicketService();
    StadiumService stadiumService=ServiceFactory.getStadiumService();
    @RequestMapping(value = "/doPay",method = RequestMethod.POST)
    public JSONObject doPay(@RequestParam("mid") String mid,
                         @RequestParam("showId") int showId,
                         @RequestParam("stadiumId") int stadiumId){
        List<Integer> tidList = ticketService.getTid(mid, showId, stadiumId,0);

        Message payMessage = stadiumService.doPay(tidList);
        JSONObject jsonObject = new JSONObject();
        if (payMessage.equals(Message.PAY_SUCCESS)) {
            jsonObject.put("code", "200");
        } else {
            jsonObject.put("code", "500");
        }
        return jsonObject;
    }
    @RequestMapping(value = "/doUnPay",method = RequestMethod.POST)
    public JSONObject doUnPay(@RequestParam("mid") String mid,
                            @RequestParam("showId") int showId,
                            @RequestParam("stadiumId") int stadiumId){
        List<Integer> tidList = ticketService.getTid(mid, showId, stadiumId,0);
        System.out.println(tidList.size());
        Message message = stadiumService.doUnPay(tidList);
        JSONObject jsonObject = new JSONObject();
        if (message.equals(Message.UNPAY_SUCCESS)) {
            jsonObject.put("code", "200");
            jsonObject.put("msg", "取消支付成功");
        } else {
            jsonObject.put("code", "500");
            jsonObject.put("msg", "取消支付失败");
        }
        return jsonObject;
    }
    @RequestMapping(value = "/doCheck",method = RequestMethod.POST)
    public JSONObject doCheck(@RequestParam("mid") String mid,
                              @RequestParam("showId") int showId,
                              @RequestParam("stadiumId") int stadiumId,
                              @RequestParam("seatRow") int seatRow,
                              @RequestParam("seatColumn") int seatColumn){
        JSONObject jsonObject = new JSONObject();
        int ticketId=ticketService.getATid(mid,showId,stadiumId,seatRow,seatColumn);
        if(ticketId==0){
            jsonObject.put("code", "404");
            jsonObject.put("msg", "没有该订单");
        }else {
            Message message = ticketService.checkTicket(ticketId);
            if (message.equals(Message.CHECK_SUCCESS)) {
                jsonObject.put("code", "200");
                jsonObject.put("msg", "成功检票");
            } else {
                jsonObject.put("code", "500");
                jsonObject.put("msg", "检票失败");
            }
        }
        return jsonObject;

    }
    @RequestMapping(value = "/getStaTickets",method = RequestMethod.POST)
    public JSONArray getStaTickets(@RequestParam("staId") String staId,@RequestParam("state") int state){
        List<StaTicket> staTickets=ticketService.getStaTicketInfo(Integer.parseInt(staId),state);
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<staTickets.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("mid",staTickets.get(i).getMid());
            jsonObject.put("tid",staTickets.get(i).getTid());
            jsonObject.put("showName",staTickets.get(i).getShowName());
            jsonObject.put("seatRow",staTickets.get(i).getSeatRow());
            jsonObject.put("seatColumn",staTickets.get(i).getSeatColumn());
            jsonObject.put("createdTime", TransDataType.DateToString(staTickets.get(i).getCreatedTime()));
            if(staTickets.get(i).getRefunedTime()==null){
                jsonObject.put("refunedTime","00");

            }else {
                jsonObject.put("refunedTime", TransDataType.DateToString(staTickets.get(i).getRefunedTime()));
            }
            jsonObject.put("picture",staTickets.get(i).getPicture());
            jsonObject.put("payPrice",staTickets.get(i).getPayPrice());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


}
