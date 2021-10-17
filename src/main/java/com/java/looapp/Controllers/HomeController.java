package com.java.looapp.Controllers;

import com.java.looapp.Services.MyUserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @Autowired
    private MyUserDetailServices service;
    @GetMapping("/")
    public String viewHomePage(){
        return "index";
    }

}
