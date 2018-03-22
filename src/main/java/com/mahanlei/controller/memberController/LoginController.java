package com.mahanlei.controller.memberController;

import com.mahanlei.Util.CodeUtil;
import com.mahanlei.Util.MailUtil;
import com.mahanlei.Util.Message;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.MemberInfo;
import com.mahanlei.service.MemberService;
import com.mahanlei.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class LoginController {
    UserService userService= ServiceFactory.getUserService();
    MemberService memberService=ServiceFactory.getMemberService();
    @RequestMapping(value = "/login" ,method=RequestMethod.POST)
    public JSONObject login(@RequestParam("account") String username, @RequestParam("password") String paw,HttpServletRequest request) {
//        System.out.println(map.get("account"));
//        log.info("登录请求...username="+map.get("account")+"  pwd=" + map.get("password"));
        JSONObject r = new JSONObject();
        String passw =  userService.getPassw(username);
        MemberInfo memberInfo=memberService.getMemberInfo(username);
        if(username==null){
            r.put("code", "404");
            r.put("msg", "用户名不能为空");
        }
        if(passw!=null&&passw.equals(paw)&&memberInfo.getState()==1){
            r.put("code", "200");
            r.put("msg", "登录成功");
        } else if (memberInfo.getState()!=1) {
            r.put("code","500");
            r.put("msg","此用户未通过邮箱验证激活");
        }else{
            r.put("code", "500");
            r.put("msg", "密码不正确或用户名不存在");
        }
        return r;
//        System.out.println(username);
    }
    @RequestMapping(value="/register",method=RequestMethod.POST)
public JSONObject register(@RequestParam("mid") String mid,@RequestParam("email") String email,
                           @RequestParam("password") String password,@RequestParam("age") int age,HttpServletRequest request){
      JSONObject jsonObject=new JSONObject();
//        System.out.println(mid);
        MemberInfo memberInfo=new MemberInfo(mid,age,email,0,0,0,  CodeUtil.generateUniqueCode());
     Message message= memberService.doRegister(memberInfo,password);
     if(message.equals(Message.REGISTER_SUCCESS)){

         new Thread(new MailUtil(email,memberInfo.getCode())).start();
         jsonObject.put("code","200");
         jsonObject.put("msg","注册成功");

     }else{
         jsonObject.put("code","500");
         jsonObject.put("msg","用户名已存在");
     }
     return jsonObject;
}
    @RequestMapping(value="/register/activeMember",method=RequestMethod.POST)
    public JSONObject activeMember(@RequestParam ("code") String code,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
//        System.out.println(code);
        Boolean b= memberService.activeMember(code);
        if(b){
            jsonObject.put("code","200");
            jsonObject.put("msg","成功激活");
        }else {
            jsonObject.put("code","500");
            jsonObject.put("msg","失败激活");
        }
        return jsonObject;
    }
    }

