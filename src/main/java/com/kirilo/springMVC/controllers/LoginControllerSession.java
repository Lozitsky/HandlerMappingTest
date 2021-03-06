package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

//https://www.intertech.com/Blog/understanding-spring-mvc-model-and-session-attributes/

@Controller
@SessionAttributes("user")
//https://www.journaldev.com/2668/spring-validation-example-mvc-validator
public class LoginControllerSession {
    private static final Logger logger = Logger.getLogger(LoginControllerSession.class);

    private static final int WEAK_STRENGTH = 1;
    private static final int AVERAGE_STRENGTH = 5;
    private static final int STRONG_STRENGTH = 7;

    private static final String WEAK_COLOR = "#FF0000";
    private static final String AVERAGE_COLOR = "#FF9900";
    private static final String STRONG_COLOR = "#0099CC";

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("user")
    private User addUser() {
        return new User();
    }

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody
    String checkStrength(@RequestParam String password, Locale locale) {
        String result = "<span style='color:%s; font-weight:bold;'>%s</span>";

        if (password.length() >= WEAK_STRENGTH && password.length() < AVERAGE_STRENGTH) {
//            return "WEAK";
            return String.format(result, WEAK_COLOR, messageSource.getMessage("password.strength.weak", new String[]{locale.getDisplayName(locale)}, locale));
        } else if (password.length() >= AVERAGE_STRENGTH && password.length() < STRONG_STRENGTH) {
//            return "AVERAGE";
            return String.format(result, AVERAGE_COLOR, messageSource.getMessage("password.strength.average", new String[]{locale.getDisplayName(locale)}, locale));
        } else if (password.length() >= STRONG_STRENGTH){
//            return "STRONG";
            return String.format(result, STRONG_COLOR, messageSource.getMessage("password.strength.strong", new String[]{locale.getDisplayName(locale)}, locale));
        }
        return "";
    }


    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String main(@ModelAttribute("user") User user, BindingResult bindingResult, Locale locale, HttpSession session) {

        if (!bindingResult.hasErrors()) {
            if (user.getName() != null && user.getPassword() != null && !user.getName().equals("") && !user.getPassword().equals("")) {
                User sessionUser = (User) session.getAttribute("user");
//                model.addAttribute("locale", messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale));
                session.setAttribute("locale", messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale));
//                return "main";
                return "redirect:/main-page";
            }
            logger.info(locale.getDisplayLanguage());
            String message = messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale);
            logger.info(message);
        }
        return "login";
    }

    //    http://www.javapractices.com/topic/TopicAction.do?Id=181
    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public String onSubmit(Locale locale, @ModelAttribute("user") @Validated User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            logger.info("Returning login.jsp page");
            return "login";
        }

        if (user != null && user.getName() != "" && user.getPassword() != "") {
            session.setAttribute("locale", messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale));
        }
        logger.info("Returning main.jsp page");
        return "redirect:/main-page";
    }

    // http://blog.niteshapte.com/2013-07-15-how-to-prevent-duplicate-form-submission-in-spring-mvc.htm
    @RequestMapping(value = "/main-page", method = RequestMethod.GET)
    public String goMainPage(HttpServletRequest request, HttpSession session, Locale locale) {

        String sessionAttribute = (String) session.getAttribute("locale");
        String message = messageSource.getMessage("locale.value", new String[]{sessionAttribute}, locale);
        logger.info("Message: " + message);

        return "main";
    }

    //    https://stackoverflow.com/questions/17955777/redirect-to-an-external-url-from-controller-action-in-spring-mvc
    @RequestMapping("/login.off")
    protected void setCompleteSession(SessionStatus status, HttpServletResponse httpServletResponse) {
        status.setComplete();
        httpServletResponse.setHeader("Location", "login.do");
        httpServletResponse.setStatus(302);
    }
}
