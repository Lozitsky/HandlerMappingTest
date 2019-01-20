package com.kirilo.springMVC.validator;

import com.kirilo.springMVC.models.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");

        if (!errors.hasErrors()) {
            if (user.getName().length() < 6 || user.getName().length() > 30) {
                errors.rejectValue("name", "name.length");
            }

            if (user.getPassword().length() < 8 || user.getName().length() > 20) {
                errors.rejectValue("password", "password.length");
            }
        }

    }
}
