package com.mahanlei.controller.manController;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class ManaLoginController {
    UserService userService= ServiceFactory.getUserService();

    @RequestMapping(value = "/manLogin", method = RequestMethod.POST)
    public JSONObject manLogin(@RequestParam("id") String id,
                               @RequestParam("password") String paw){
        JSONObject r = new JSONObject();
        String passw =  userService.getPassw(id);

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
