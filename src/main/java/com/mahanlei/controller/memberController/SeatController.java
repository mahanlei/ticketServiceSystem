package com.mahanlei.controller.memberController;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.Seat;
import com.mahanlei.service.TicketService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/seat")
public class SeatController {
    TicketService ticketService= ServiceFactory.getTicketService();
    @RequestMapping(value = "/getSeatList",method= RequestMethod.POST)
    public JSONArray getSeatList(@RequestParam("showId") int showId,
                                  @RequestParam("stadiumId") int stadiumId,
                                  @RequestParam("row") int row){
        JSONArray jsonArray=new JSONArray();
        List<Seat> seatList=ticketService.getSeats(showId,stadiumId,row);
        for(int i=0;i<seatList.size();i++ ){
            JSONObject jsonObject=new JSONObject();
jsonObject.put("value",seatList.get(i).getSeatColumn());
jsonObject.put("label","第"+seatList.get(i).getSeatColumn()+"列");
if(seatList.get(i).getState()==0){
    jsonObject.put("disabled",false);

}else  jsonObject.put("disabled",true);
jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping(value = "/getSeatPrice",method = RequestMethod.POST)
    public JSONObject getSeatPrice(@RequestParam("showId") int showId,
                                  @RequestParam("stadiumId") int stadiumId,
                                  @RequestParam("row") int row,
                                  @RequestParam("column") int column){
        JSONObject jsonObject=new JSONObject();
        double price =ticketService.selectSeat(new Seat(showId,stadiumId,row,column,0));
        jsonObject.put("price",price);
        return jsonObject;
    }
}
