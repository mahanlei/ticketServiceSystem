package com.mahanlei.factory;

import com.mahanlei.service.MemberService;
import com.mahanlei.service.UserService;
import com.mahanlei.service.impl.MemberServiceImpl;
import com.mahanlei.service.impl.UserServiceImpl;

public class ServiceFactory {
    public  static UserService getUserService(){
     return    UserServiceImpl.getInstance();
    }
    public static MemberService getMemberService(){
        return MemberServiceImpl.getInstance();
    }
}
