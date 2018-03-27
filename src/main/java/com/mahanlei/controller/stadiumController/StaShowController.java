package com.mahanlei.controller.stadiumController;

import com.mahanlei.Util.Message;
import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.service.SeatService;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.StadiumService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/stadium")
public class StaShowController {
ShowService showService=ServiceFactory.getShowService();
SeatService seatService=ServiceFactory.getSeatService();
@RequestMapping(value = "/getExistShow",method = RequestMethod.POST)
    public JSONArray getExistShow (@RequestParam("staId") String staId){
    List<ShowInfo> showInfoList=showService.getStaShow(staId);
    JSONArray jsonArray=new JSONArray();
    for(int i=0;i<showInfoList.size();i++){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("showId",showInfoList.get(i).getShowId());
        jsonObject.put("showName",showInfoList.get(i).getName());
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(showInfoList.get(i).getStartTime());
        jsonObject.put("startTime",dateStr);
        String dateStr1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(showInfoList.get(i).getEndTime());
        jsonObject.put("endTime",dateStr1);
        jsonObject.put("type", TransDataType.showTypeToString(showInfoList.get(i).getType()));
        jsonObject.put("picture",showInfoList.get(i).getPicture());
       jsonObject.put("description",showInfoList.get(i).getDescription());
       jsonObject.put("showState",showInfoList.get(i).getShowState());
       jsonArray.add(jsonObject);
    }
    return jsonArray;
}
@RequestMapping(value="/publishShow",method = RequestMethod.POST)
    public JSONObject publishShow(@RequestParam("stadiumId") int stadiumId,
                                  @RequestParam("showName") String showName,
                                  @RequestParam("type") int type,
                                  @RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime,
                                  @RequestParam("description") String description,
                                  @RequestParam("price") double price){
    JSONObject jsonObject=new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date start=null;
    Date end=null;
    String picture="https://images.unsplash.com/photo-1517333941-389292d513d2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=3d9c4b6bbeac75c65ff12038fe85ddf3&auto=format&fit=crop&w=1866&q=80";
    try {
       start=formatter.parse(startTime);
  end=formatter.parse(endTime);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    ShowInfo showInfo=new ShowInfo(showName,stadiumId,start,end,TransDataType.intToShowType(type),picture,description,0);
    Message message=showService.releaseAShow(showInfo);
    Message message1=seatService.addSeats(showName,stadiumId,start,price);

if(message.equals(Message.RELEASE_SUCCESS)&&message1.equals(Message.UPDATE_SUCCESS)){
    jsonObject.put("code","200");
    jsonObject.put("msg","发布成功");
}else {
    jsonObject.put("code","500");
    jsonObject.put("msg","发布失败");
}

    return jsonObject;
}
}
