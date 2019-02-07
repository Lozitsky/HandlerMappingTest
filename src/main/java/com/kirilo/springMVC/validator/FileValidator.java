package com.kirilo.springMVC.validator;

import com.kirilo.springMVC.models.UploadedFile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//@Component
public class FileValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return UploadedFile.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        UploadedFile bucket = (UploadedFile) target;

        if (bucket.getFile() != null && bucket.getFile().isEmpty()){
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a file");
        }

/*        if (files.getFiles().length == 0){
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a file");
        } else {
            for (MultipartFile file : files.getFiles()) {
                if (file.getSize() == 0) {
                    errors.rejectValue("file", "uploadForm.selectFile", "Please select a file");
                }
            }
        }*/
    }
}
