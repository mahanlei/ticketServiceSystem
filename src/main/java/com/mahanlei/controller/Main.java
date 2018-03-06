package com.mahanlei.controller;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.service.UserService;

public class Main {
    public static void  main(String args[]){
        String uid="ml";
        UserService userService= ServiceFactory.getUserService();
       System.out.print( userService.getPassw(uid));
    }

}
