package com.kirilo.springMVC.interceptors;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//https://examples.javacodegeeks.com/enterprise-java/spring/mvc/spring-mvc-interceptor-tutorial/
//https://examples.javacodegeeks.com/enterprise-java/spring/mvc/spring-mvc-handler-interceptors-example/
public class TimeInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("GET") || request.getMethod().equals("POST")) {
            long millis = System.currentTimeMillis();
            request.setAttribute("millis", millis);
            logger.info("preHandle method, time in ms is " + millis);
            return true;
        } else return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long totalMillis = System.currentTimeMillis() - (Long) request.getAttribute("millis");
        logger.info("postHandle method: total time is " + totalMillis + " ms");
    }
}
