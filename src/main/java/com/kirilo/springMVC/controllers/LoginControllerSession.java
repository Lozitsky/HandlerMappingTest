package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletResponse;

//https://www.intertech.com/Blog/understanding-spring-mvc-model-and-session-attributes/

@Controller
@SessionAttributes("user")
//https://www.journaldev.com/2668/spring-validation-example-mvc-validator
public class LoginControllerSession {
    private static final Logger logger = Logger.getLogger(LoginControllerSession.class);

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("user")
    private User addUser(){
        return new User();
    }

    @RequestMapping("/login.do")
    protected String onSubmit(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning login.jsp page");
            return "login";
        }
        logger.info("Returning main.jsp page");
        return "main";
    }

//    https://stackoverflow.com/questions/17955777/redirect-to-an-external-url-from-controller-action-in-spring-mvc
    @RequestMapping("/login.off")
    protected void setCompleteSession(SessionStatus status, HttpServletResponse httpServletResponse){
        status.setComplete();
        httpServletResponse.setHeader("Location", "login.do");
        httpServletResponse.setStatus(302);
    }
}
