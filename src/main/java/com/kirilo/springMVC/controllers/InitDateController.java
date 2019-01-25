package com.kirilo.springMVC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class InitDateController {
    //modelAndView.addObject("now", null);
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public void initNowAttribute(Date date, Model model) {
        model.addAttribute("title", "Init");
        model.addAttribute("now", date);
    }
}
