package com.mahanlei.controller.memberController;

import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.MemberInfo;
import com.mahanlei.service.MemberService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class MemberProfileController {
    MemberService memberService= ServiceFactory.getMemberService();
    @RequestMapping(value = "/profile",method= RequestMethod.POST)
    public JSONObject profile(@RequestParam("mid") String mid){
        System.out.println(mid);
        MemberInfo memberInfo=memberService.getMemberInfo(mid);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("age",memberInfo.getAge());
        jsonObject.put("email",memberInfo.getEmail());
        jsonObject.put("rank",memberInfo.getRank());
        jsonObject.put("points",memberInfo.getPoints());
        String stateString="非会员";
        if (memberInfo.getState()==1){
            stateString="会员";
        }
            jsonObject.put("state",stateString);
        return jsonObject;
    }
    @RequestMapping(value = "/profile/change",method= RequestMethod.POST)
    public JSONObject changeProfile(@RequestParam("mid") String mid,@RequestParam("age") int age,
                                    @RequestParam("state") String stateStr){
        JSONObject jsonObject=new JSONObject();
       Message message= memberService.changeProfile(mid,age,stateStr);
       if(message.equals(Message.UPDATE_SUCCESS)){
           jsonObject.put("code", "200");
           jsonObject.put("msg", "更新成功");
       }else {
           jsonObject.put("code", "500");
           jsonObject.put("msg", "更新失败");
       }
       return jsonObject;
    }
}
