package com.kirilo.springMVC.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Date;

//https://blog.trifork.com/2011/12/08/use-immutable-objects-in-your-spring-mvc-controller-by-implementing-your-own-webargumentresolver/
public class ContextExtractingWebArgumentResolver implements WebArgumentResolver {
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        if (Date.class.equals(methodParameter.getParameterType())) {
            return new Date();
        }
        return UNRESOLVED;
    }
}
