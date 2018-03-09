package com.mahanlei.controller;

import com.mahanlei.Util.MailUtil;
import com.mahanlei.Util.TransDataType;
import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.model.ShowInfoBrief;
import com.mahanlei.service.MemberService;
import com.mahanlei.service.ShowService;
import com.mahanlei.service.UserService;
import net.sf.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;

public class MainTest {
    public static void  main(String args[]){
//        String uid="123";
//        UserService userService= ServiceFactory.getUserService();
//        MemberService memberService=ServiceFactory.getMemberService();
//       System.out.print( memberService.doRegister("mmmm","111",123,"123"));
//        MailUtil mailUtil=new MailUtil("13585141983@163.com","8");
//        new Thread(new MailUtil("13585141983@163.com", "8")).start();
//        System.out.println(memberService.getMemberInfo("123").getEmail());
        ShowService showService=ServiceFactory.getShowService();
//        List<ShowInfoBrief> list=showService.getAllShowInfo();
//        for(int i=0;i<list.size();i++){
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("showId",list.get(i).getShowId());
//            jsonObject.put("name",list.get(i).getName());
//            jsonObject.put("type", TransDataType.showTypeToInt(list.get(i).getType()));
//            jsonObject.put("picture",list.get(i).getPicture());
//            System.out.println(jsonObject);
//        }
        ShowInfo showInfo=showService.getShowInfo(1);
        System.out.println(new Timestamp(showInfo.getStartTime().getTime()));
        System.out.println(showInfo.getStaName());
        System.out.println(showInfo.getAddress());
        System.out.println(showInfo.getName());
        System.out.println(TransDataType.showTypeToInt(showInfo.getType()));

    }

}
