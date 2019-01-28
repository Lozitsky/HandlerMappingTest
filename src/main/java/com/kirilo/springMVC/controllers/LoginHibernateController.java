package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoginHibernateController {
    @Autowired
    @Qualifier("validator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/login.dto")
    public String postLoginForm(@ModelAttribute("loginDto") @Valid LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "logindto";
        }
        return "main";
    }
}
