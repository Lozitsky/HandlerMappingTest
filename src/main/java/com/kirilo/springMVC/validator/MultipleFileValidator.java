package com.kirilo.springMVC.validator;

import com.kirilo.springMVC.models.MultiUploadedFile;
import com.kirilo.springMVC.models.UploadedFile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MultipleFileValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return  MultiUploadedFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultiUploadedFile multiUploadedFile = (MultiUploadedFile) target;

        int index = 0;
        for (UploadedFile uploadedFile : multiUploadedFile.getFiles()) {
            if (uploadedFile.getFile() != null && uploadedFile.getFile().isEmpty()) {
                errors.rejectValue("files[" + index + "].file", "uploadForm.selectFile");
            }
            index++;
        }
    }
}
