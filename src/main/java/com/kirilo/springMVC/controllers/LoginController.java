package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

//https://www.javainterviewpoint.com/spring-mvc-simpleformcontroller-example/
@SuppressWarnings("deprecation")
public class LoginController extends SimpleFormController {

    public LoginController() {
        setCommandClass(User.class);
        setCommandName("user");
    }

    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        User user = (User) command;
        return new ModelAndView("main", "user", user);
    }

}
