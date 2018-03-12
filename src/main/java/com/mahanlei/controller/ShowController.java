package com.mahanlei.controller;

import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.model.ShowInfoBrief;
import com.mahanlei.service.ShowService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")
@RequestMapping(value = "/show")
        public class ShowController {

    ShowService showService= ServiceFactory.getShowService();
    @RequestMapping(value = "/showList" ,method= RequestMethod.POST)
    public JSONArray showList(){
        JSONArray jsonArray=new JSONArray();

        List<ShowInfoBrief> list=showService.getAllShowInfo();
        for(int i=0;i<list.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("showId",list.get(i).getShowId());
            jsonObject.put("name",list.get(i).getName());
            jsonObject.put("type", TransDataType.showTypeToString(list.get(i).getType()));
            jsonObject.put("picture",list.get(i).getPicture());
            jsonArray.add(jsonObject);
        }
        return jsonArray;

    }
    @RequestMapping(value = "/showInfo" ,method= RequestMethod.POST)
    public  JSONObject getShowInfo(@RequestParam ("showId") int showId){
        JSONObject jsonObject=new JSONObject();
        ShowInfo showInfo=showService.getShowInfo(showId);
        jsonObject.put("stadiumId",showInfo.getStadiumId());
        jsonObject.put("name",showInfo.getName());
        jsonObject.put("staName",showInfo.getStaName());
        jsonObject.put("address",showInfo.getAddress());
        jsonObject.put("seatRows",showInfo.getSeatRows());
        jsonObject.put("seatColumns",showInfo.getSeatColumns());
        jsonObject.put("startTime",TransDataType.DateToString(showInfo.getStartTime()));
        jsonObject.put("endTime",TransDataType.DateToString(showInfo.getEndTime()));
        jsonObject.put("type",TransDataType.showTypeToString(showInfo.getType()));
        jsonObject.put("picture",showInfo.getPicture());
        jsonObject.put("description",showInfo.getDescription());
        return jsonObject;
    }


    @RequestMapping(value = "/releaseShow",method = RequestMethod.POST)
    public JSONObject releaseShow(){
        JSONObject jsonObject=new JSONObject();
        return jsonObject;
    }

}
