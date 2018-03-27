package com.mahanlei.controller.stadiumController;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.StadiumInfo;
import com.mahanlei.service.StadiumService;
import com.mahanlei.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
@RequestMapping(value = "/stadium")
public class StaLoginController {
    StadiumService stadiumService= ServiceFactory.getStadiumService();
    UserService userService=ServiceFactory.getUserService();
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject staLogin(@RequestParam("staId") String staId,
                               @RequestParam("password") String paw){
        JSONObject r = new JSONObject();
        String passw =  userService.getPassw(staId);
        StadiumInfo stadiumInfo=stadiumService.getStadiumInfo(Integer.parseInt(staId));
        if(staId==null){
            r.put("code", "404");
            r.put("msg", "场馆编号不能为空");
        }
        if(passw!=null&&passw.equals(paw)&&stadiumInfo.getState()!=0){
            r.put("code", "200");
            r.put("msg", "登录成功");
        } else if (stadiumInfo.getState()==0) {
            r.put("code","500");
            r.put("msg","此场馆注册申请未通过审批");
        }else{
            r.put("code", "500");
            r.put("msg", "密码不正确或场馆编号不存在");
        }
        return r;
    }
    @RequestMapping(value = "/checkState",method = RequestMethod.POST)
    public JSONObject checkStaState(@RequestParam("staId") String staId){
        JSONObject jsonObject=new JSONObject();
        StadiumInfo stadiumInfo=stadiumService.getStadiumInfo(Integer.parseInt(staId));
        jsonObject.put("staState",stadiumInfo.getState());
        return jsonObject;
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public JSONObject staRegister(@RequestParam("staName") String staName,
                                  @RequestParam("address") String address,
                                  @RequestParam("seatRows") int seatsRows,
                                  @RequestParam("seatColumns") int seatColumns,
    @RequestParam("password") String password){

        JSONObject jsonObject=new JSONObject();
        StadiumInfo stadiumInfo=new StadiumInfo(staName,address,seatsRows,seatColumns,0);
Message message=stadiumService.addStadium(stadiumInfo,password);

if(message.equals(Message.REGISTER_SUCCESS)){
    jsonObject.put("code", "200");
    jsonObject.put("msg", "注册成功，等待审批");
}else{
    jsonObject.put("code", "500");
    jsonObject.put("msg", "注册失败");
}
return jsonObject;

    }

}

