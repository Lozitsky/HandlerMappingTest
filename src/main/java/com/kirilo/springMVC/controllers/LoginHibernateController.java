package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.LoginDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("loginDto")
public class LoginHibernateController {
    private static final Logger logger = Logger.getLogger(LoginHibernateController.class);

    @ModelAttribute("loginDto")
    LoginDTO addLoginDTO() {
        return new LoginDTO();
    }

    @Autowired
    @Qualifier("validator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login.dto", method = RequestMethod.GET)
    public String main(@ModelAttribute("loginDto") LoginDTO loginDTO, BindingResult bindingResult, Locale locale, Model model) {

        if (!bindingResult.hasErrors()) {

            if (loginDTO.getName() != null && loginDTO.getPassword() != null && !loginDTO.getName().equals("") && !loginDTO.getPassword().equals("")) {
                model.addAttribute("locale", messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale));
                return "redirect:/mainpage.dto";
            }
            logger.info(locale.getDisplayLanguage());
            String message = messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale);
            logger.info(message);
        }
        return "logindto";
    }

    @RequestMapping(value = "/check-user.dto", method = RequestMethod.POST)
    public ModelAndView postLoginForm(Locale locale, @ModelAttribute("loginDto") @Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes attributes) {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            logger.info("Returning logindto.jsp page");
            modelAndView.setViewName("logindto");
        }

        if (loginDTO != null && loginDTO.getName() != "" && loginDTO.getPassword() != "") {
            RedirectView redirectView = new RedirectView("mainpage.dto");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
            attributes.addFlashAttribute("locale", messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale));
        }
        logger.info("Returning main.dto.jsp page");
        return modelAndView;
    }

    @RequestMapping(value = "/mainpage.dto", method = RequestMethod.GET)
    public String goMainPage(HttpServletRequest request, Model model, Locale locale){
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        model.addAttribute("locale", messageSource.getMessage("locale.value", new String[]{locale.getDisplayName(locale)}, locale));
        if (flashMap != null) {
            logger.info("redirect!");
        } else {
            logger.info("update!");
        }
        return "multiple-main.dto";
    }

    @RequestMapping("/logoff")
    protected void setCompleteSession(SessionStatus status, HttpServletResponse httpServletResponse) {
        status.setComplete();
        httpServletResponse.setHeader("Location", "login.dto");
        httpServletResponse.setStatus(302);
    }
}
