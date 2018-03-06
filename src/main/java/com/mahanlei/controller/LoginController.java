package com.mahanlei.controller;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.service.UserService;
import com.mahanlei.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//@Controller
@RestController

@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    UserService userService= ServiceFactory.getUserService();
    @RequestMapping(value = "/login" ,method=RequestMethod.POST)
    public JSONObject login(@RequestParam("account") String username, @RequestParam("password") String paw,HttpServletRequest request) {
//        System.out.println(map.get("account"));
//        log.info("登录请求...username="+map.get("account")+"  pwd=" + map.get("password"));
        JSONObject r = new JSONObject();
        String passw =  userService.getPassw(username);
        if(username==null){
            r.put("code", "404");
            r.put("msg", "用户名不能为空");
        }
        if(passw!=null&&passw.equals(paw)){
            r.put("code", "200");
            r.put("msg", "登录成功");
//            r.put("token", TokenUtil.getToken(username));
        }else if(passw==null){
            r.put("code", "500");
            r.put("msg", "密码不正确或用户名不存在");
        }else{
            r.put("code", "500");
            r.put("msg", "密码不正确或用户名不存在");
        }
        return r;
//        System.out.println(username);
    }

    }

