package com.mahanlei.controller.stadiumController;

import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.StadiumService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/stadium")
public class StaShowController {
    StadiumService stadiumService= ServiceFactory.getStadiumService();
ShowService showService=ServiceFactory.getShowService();
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
}
