package com.mahanlei.controller.stadiumController;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.StadiumInfo;
import com.mahanlei.service.StadiumService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/stadium")
public class StaInfoController {
    StadiumService stadiumService= ServiceFactory.getStadiumService();
    @RequestMapping(value = "/getStaInfo",method = RequestMethod.POST)
    public JSONObject changeStaInfo(@RequestParam("staId") String  staId){
        JSONObject jsonObject=new JSONObject();
      StadiumInfo stadiumInfo=stadiumService.getStadiumInfo(Integer.parseInt(staId));
        jsonObject.put("staName",stadiumInfo.getStaName());
        jsonObject.put("address",stadiumInfo.getAddress());
        jsonObject.put("seatRows",stadiumInfo.getSeatRows());
        jsonObject.put("seatColumns",stadiumInfo.getSeatColumns());
        return jsonObject;
    }

    @RequestMapping(value = "/changeInfo",method = RequestMethod.POST)
    public JSONObject changeStaInfo(@RequestParam("staId") String  staId,
                                    @RequestParam("staName") String staName){
        JSONObject jsonObject=new JSONObject();
        Message message=stadiumService.updateStaInfo(Integer.parseInt(staId),staName);
        if(message.equals(Message.UPDATE_SUCCESS)){
            jsonObject.put("code","200");
            jsonObject.put("msg","成功提交修改申请");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("msg","提交修改申请失败");
        }
        return jsonObject;
    }

}
