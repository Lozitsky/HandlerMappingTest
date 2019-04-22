package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.exceptions.UserNoContentException;
import com.kirilo.springMVC.exceptions.UserNotFoundException;
import com.kirilo.springMVC.models.ExceptionJSONInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

//https://www.journaldev.com/2651/spring-mvc-exception-handling-controlleradvice-exceptionhandler-handlerexceptionresolver
@Controller
public class ExceptionsController {
    private static final Logger logger = Logger.getLogger(ExceptionsController.class);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable int id) throws Exception {
        if (id == 1) {
            throw new UserNoContentException(id);
        } else if (id == 2) {
            throw new UserNotFoundException(id);
        } else if (id == 3) {
            throw new SQLException("SQLException, id=" + id);
        } else if (id == 4) {
            throw new IOException("IOException, id=" + id);
        } else if (id == 5) {
            throw new UnsupportedOperationException("UnsupportedOperationException, id=" + id);
        }

        else if (id == 10) {
            return "redirect:/login.do";
        } else {
            throw new Exception("Generic Exception, id=" + id);
        }
    }

    @ExceptionHandler(UserNoContentException.class)
    public ModelAndView handleUserNoContentException(HttpServletRequest request, Exception e) {

        logger.error("Requested URL=" + request.getRequestURL());
        logger.error("Exception Raised=" + e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseBody
    public ExceptionJSONInfo handleUserNotFoundException(HttpServletRequest request, Exception e) {
        ExceptionJSONInfo jsonInfo = new ExceptionJSONInfo();
        jsonInfo.setUrl(request.getRequestURL().toString());
        jsonInfo.setMessage(e.getMessage());
        return jsonInfo;
    }
}
