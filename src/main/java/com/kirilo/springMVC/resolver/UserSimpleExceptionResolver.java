package com.kirilo.springMVC.resolver;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;

public class UserSimpleExceptionResolver extends SimpleMappingExceptionResolver {
    @Override
    protected String buildLogMessage(Exception ex, HttpServletRequest request) {
        return "Request from: " + request.getRequestURL() + '\n' + ex.getLocalizedMessage();
    }
}
