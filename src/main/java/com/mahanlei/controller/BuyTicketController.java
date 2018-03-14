package com.mahanlei.controller;

import com.mahanlei.factory.ServiceFactory;
import com.mahanlei.service.TicketService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:8081")//允许跨域请求
public class BuyTicketController {
TicketService ticketService= ServiceFactory.getTicketService();
//    @RequestMapping(value = "/getSeat",method= RequestMethod.POST)
}
