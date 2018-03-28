package com.mahanlei.controller.manController;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.StadiumShows;
import com.mahanlei.model.Ticket;
import com.mahanlei.model.TicketCount;
import com.mahanlei.model.TicketPrice;
import com.mahanlei.service.StaticsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class ManStaticsController {
    StaticsService staticsService= ServiceFactory.getStaticsService();
    @RequestMapping(value = "/getTicketCount",method = RequestMethod.POST)
    public JSONArray getTicketCount (){
        List<TicketCount> ticketCounts=staticsService.getTicketCount();
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<ticketCounts.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("time",ticketCounts.get(i).getTime());
            jsonObject.put("number",ticketCounts.get(i).getNumber());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @RequestMapping(value = "/getTicketPrice",method = RequestMethod.POST)
    public JSONArray getTicketPrice(){
        List<TicketPrice> ticketCounts=staticsService.getTicketPrice();
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<ticketCounts.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("time",ticketCounts.get(i).getTime());
            jsonObject.put("price",ticketCounts.get(i).getPrice());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping(value = "/countStaShows",method = RequestMethod.POST)
    public JSONArray countStaShows(){
        List<StadiumShows> stadiumShows=staticsService.getStadiumShows();
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<stadiumShows.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("staId",stadiumShows.get(i).getStadiumId());
            jsonObject.put("number",stadiumShows.get(i).getNumber());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping(value = "/countMember",method = RequestMethod.POST)
    public JSONObject countMember(){
      int [] numbers=staticsService.countMemberByAge();
        JSONObject jsonObject=new JSONObject();
        int total=0;
        for(int i=0;i<numbers.length;i++){
           total=total+numbers[i];
        }
        jsonObject.put("total",total);
        jsonObject.put("numbers",numbers);
        return jsonObject;
    }
}
