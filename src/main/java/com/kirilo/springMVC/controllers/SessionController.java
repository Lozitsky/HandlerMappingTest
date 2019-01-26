package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.MyCommandBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Controller
@SessionAttributes("myRequestObject")
public class SessionController {

	private static final Logger logger = Logger.getLogger(SessionController.class);
	
	@ModelAttribute("myRequestObject")
	public MyCommandBean addStuffToRequestScope() {
		logger.info("Inside of addStuffToRequestScope");
		MyCommandBean bean = new MyCommandBean("Hello World",42);
		return bean;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/dosomething")
	public String requestHandlingMethod(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("Inside of dosomething handler method");

		logger.info("--- Model data ---");
		Map modelMap = model.asMap();
		for (Object modelKey : modelMap.keySet()) {
			Object modelValue = modelMap.get(modelKey);
			logger.info(modelKey + " -- " + modelValue);
		}

		logger.info("=== Request data ===");
		Enumeration<String> reqEnum = request.getAttributeNames();
		while (reqEnum.hasMoreElements()) {
			String s = reqEnum.nextElement();
			logger.info(s);
			logger.info("==" + request.getAttribute(s));
		}

		logger.info("*** Session data ***");
		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()){
			String s = e.nextElement();
			logger.info(s);
			logger.info("**" + session.getAttribute(s));
		}
		
		return "nextpage";
	}
	
	@RequestMapping("/endsession")
	public String nextHandlingMethod2(SessionStatus status){
		status.setComplete();
		return "lastpage";
	}

}
