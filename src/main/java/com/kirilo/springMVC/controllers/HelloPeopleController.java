package com.kirilo.springMVC.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloPeopleController extends AbstractController {
    private static final Logger logger = Logger.getLogger(HelloPeopleController.class);

    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest,
                                                 HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView("hello", "msg", "Hello Message!");
        modelAndView.addObject("title", "Hello Title");
        logger.info("HelloPeopleController is invoked! ");

        return modelAndView;
    }
}
