package com.mahanlei.controller;

import com.mahanlei.Util.MailUtil;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.service.MemberService;
import com.mahanlei.service.UserService;

public class Main {
    public static void  main(String args[]){
//        String uid="123";
//        UserService userService= ServiceFactory.getUserService();
//        MemberService memberService=ServiceFactory.getMemberService();
//       System.out.print( memberService.doRegister("mmmm","111",123,"123"));
//        MailUtil mailUtil=new MailUtil("13585141983@163.com","8");
        new Thread(new MailUtil("13585141983@163.com", "8")).start();
    }

}
