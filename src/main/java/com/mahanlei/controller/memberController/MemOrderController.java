package com.mahanlei.controller.memberController;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.TicketInfoBrief;
import com.mahanlei.service.TicketService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求

public class MemOrderController {
    TicketService ticketService= ServiceFactory.getTicketService();

    @RequestMapping(value = "/getOrderList",method = RequestMethod.POST)
    public JSONArray getOrderList (@RequestParam("mid") String mid,
                                   @RequestParam("state") int state ) {
        List<TicketInfoBrief> ticketInfoBriefList = ticketService.getMyTicketInfo(mid, state);
        JSONArray jsonArray = new JSONArray();
        if (ticketInfoBriefList.size() != 0) {
            for (int i = 0; i < ticketInfoBriefList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tid",ticketInfoBriefList.get(i).getTid());
                jsonObject.put("showName", ticketInfoBriefList.get(i).getShowName());
                jsonObject.put("staName", ticketInfoBriefList.get(i).getStaName());
                jsonObject.put("picture", ticketInfoBriefList.get(i).getPicture());
                jsonObject.put("seatRow", ticketInfoBriefList.get(i).getSeatRow());
                jsonObject.put("seatColumn", ticketInfoBriefList.get(i).getSeatColumn());
                Date createdTime=ticketInfoBriefList.get(i).getCreatedTime();
                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdTime);
                jsonObject.put("createdTime", dateStr);
                Date refunedTime=ticketInfoBriefList.get(i).getRefunedTime();
                if(refunedTime!=null){
                    String dateStr1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(refunedTime);
                    jsonObject.put("refunedTime", dateStr1);
                }else {
                    jsonObject.put("refunedTime", '0');
                }

                jsonObject.put("payPrice", ticketInfoBriefList.get(i).getPayPrice());
System.out.println(ticketInfoBriefList.get(i).getPayPrice());
                jsonArray.add(jsonObject);

            }
        }
        return jsonArray;
    }
    @RequestMapping(value = "/doRefund" ,method = RequestMethod.POST)
    public JSONObject doRefund(@RequestParam  ("tid") int tid){
        JSONObject jsonObject=new JSONObject();
       Message message= ticketService.refuned(tid);
       if(message.equals(Message.REFUNED_SUCCESS)){
           jsonObject.put("code","200");
           jsonObject.put("msg","退订成功");

       }else{
           jsonObject.put("code","500");
           jsonObject.put("msg","退订失败，请再次尝试");
       }
       return jsonObject;
    }

}
