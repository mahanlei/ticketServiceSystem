package com.mahanlei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class IndexController {

    @RequestMapping(value="/index")
    public String index() {
        return "index";
    }
}

