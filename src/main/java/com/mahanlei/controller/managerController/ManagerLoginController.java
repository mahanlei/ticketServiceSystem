package com.mahanlei.controller.managerController;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins="http://localhost:8081")
@RequestMapping(value = "/manager")
public class ManagerLoginController {
    UserService userService= ServiceFactory.getUserService();
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject manLogin(@RequestParam("id") String id,
                               @RequestParam("password") String paw){
        JSONObject r = new JSONObject();
        String passw =  userService.getPassw(id);
        if(id==null){
            r.put("code", "404");
            r.put("msg", "用户名不能为空");
        }
        if(passw!=null&&passw.equals(paw)){
            r.put("code", "200");
            r.put("msg", "登录成功");
        }else{
            r.put("code", "500");
            r.put("msg", "密码不正确");
        }
        return r;
    }
}
