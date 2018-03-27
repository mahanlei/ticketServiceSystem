package com.mahanlei.controller.stadiumController;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.SoldTicket;
import com.mahanlei.service.StadiumService;
import com.mahanlei.service.StaticsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/stadium")
public class StaStaticsController {
    StaticsService staticsService= ServiceFactory.getStaticsService();
    @RequestMapping(value = "/getSoldRate",method = RequestMethod.POST)
    public JSONArray getSoldRate(@RequestParam("staId") String staId){
        List<SoldTicket> soldTickets=staticsService.getSoldTicketRate(Integer.parseInt(staId));
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i< soldTickets.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("showId",soldTickets.get(i).getShowId());
            jsonObject.put("rate",soldTickets.get(i).getRate());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping(value = "/getUpSeatRate",method = RequestMethod.POST)
    public JSONArray getUpSeatRate(@RequestParam("staId") String staId){
        List<SoldTicket> soldTickets=staticsService.getAttendenceRate(Integer.parseInt(staId));
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i< soldTickets.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("showId",soldTickets.get(i).getShowId());
            jsonObject.put("rate",soldTickets.get(i).getRate());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping(value = "/getProfit",method = RequestMethod.POST)
    public JSONObject getProfit(@RequestParam("id") String id){
       double profit=staticsService.getProfit(id);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("profit",profit);

        return jsonObject;
    }

}
