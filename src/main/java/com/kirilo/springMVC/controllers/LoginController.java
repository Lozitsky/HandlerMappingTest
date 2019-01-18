package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.User;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

//https://www.javainterviewpoint.com/spring-mvc-simpleformcontroller-example/
@SuppressWarnings("deprecation")
public class LoginController extends SimpleFormController {
    private static final Logger logger = Logger.getLogger(LoginController.class);

    public LoginController() {
        setCommandClass(User.class);
        setCommandName("user");
    }

    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        User user = (User) command;
        logger.info("LoginController is invoked! ");
        return new ModelAndView("main", "user", user);
    }

}
