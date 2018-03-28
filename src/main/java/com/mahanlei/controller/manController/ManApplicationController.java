package com.mahanlei.controller.manController;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.Application;
import com.mahanlei.service.ApplicationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class ManApplicationController {
    ApplicationService applicationService= ServiceFactory.getApplicationService();
    @RequestMapping(value = "/getApplications",method = RequestMethod.POST)
    public JSONArray getApplications (@RequestParam("type") int type,
                                      @RequestParam("state") int state){
        List<Application> applicationList=applicationService.getApplications(type,state);
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<applicationList.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("aid",applicationList.get(i).getAid());
            jsonObject.put("stadiumId",applicationList.get(i).getStadiumId());
            jsonObject.put("staName",applicationList.get(i).getStaName());
            jsonObject.put("address",applicationList.get(i).getAddress());
            jsonObject.put("seatRows",applicationList.get(i).getSeatRows());
            jsonObject.put("seatColumns",applicationList.get(i).getSeatColumns());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @RequestMapping(value = "/agreeApplication",method = RequestMethod.POST)
    public JSONObject agreeApplications (@RequestParam("aid") int aid,
                                      @RequestParam("stadiumId") int stadiumId){
        Message message=applicationService.agreeApplication(aid,stadiumId);
        JSONObject jsonObject=new JSONObject();
        if(message.equals(Message.UPDATE_SUCCESS)){
            jsonObject.put("code","200");
            jsonObject.put("msg","操作成功");
        } else {
            jsonObject.put("code","500");
            jsonObject.put("msg","失败");

        }
        return jsonObject;
    }
}
