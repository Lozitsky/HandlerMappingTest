package com.kirilo.springMVC.controllers;

import com.kirilo.springMVC.models.MultiUploadedFile;
import com.kirilo.springMVC.models.UploadedFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// https://memorynotfound.com/spring-mvc-file-upload-example-validator/
@Controller
@SessionAttributes("uploadedFile")
public class SingleFileController {
    private static final Logger logger = Logger.getLogger(SingleFileController.class);

    @ModelAttribute("uploadedFile")
    UploadedFile addUploadedFile() {
        return new UploadedFile();
    }

    @ModelAttribute("multiUploadedFile")
    MultiUploadedFile addMultiUploadedFile(){
        return new MultiUploadedFile(3);
    }

    @Autowired
    @Qualifier("fileValidator")
    private Validator fileValidator;

    @Autowired
    @Qualifier("multipleFileValidator")
    private Validator multipleFV;

//    https://stackoverflow.com/questions/3721122/spring-validation-with-valid/3721343#3721343
    @InitBinder("uploadedFile")
    private void initBinderFile(WebDataBinder binder) {
        binder.setValidator(fileValidator);
//        binder.setValidator(multipleFV);
    }

    @InitBinder("multiUploadedFile")
    private void initBinderMultipleFile(WebDataBinder binder) {
        binder.setValidator(multipleFV);
    }

/*    @ModelAttribute("files")
    private UploadedFile getModelAttributes(){
        return new UploadedFile();
    }*/

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    public String uploadFileHandler(@ModelAttribute("uploadedFile") @Validated UploadedFile uploadedFile, BindingResult result, HttpSession session) {
        String message = "";
        if (result.hasErrors()) {
            return "main.dto";
        }

/*        UploadedFile uploadForm = (UploadedFile) session.getAttribute("uploadForm");
        MultipartFile file = uploadForm.getFile();*/

        MultipartFile file = uploadedFile.getFile();

        if (file!= null && !file.isEmpty()) {
            message = getNextFile(file);
        } else {
            message = "You failed to upload because the file is empty!";
        }
        session.setAttribute("message", message);
        return "redirect:/files-main-page";
    }

    @RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
    public String uploadMultipleFileHandler(@Validated @ModelAttribute("multiUploadedFile") MultiUploadedFile form, BindingResult bindingResult, HttpSession session) {
        String message = "";

        if (bindingResult.hasErrors()) {
            return "multiple-main.dto";
//            return "redirect:/mainpage.dto";
//            return "redirect:/uploadMultipleFile";
        }

/*        if (bindingResult.hasErrors()) {
            message = "You failed to upload because the files are empty!";
        }*/ else if (form != null) {
            logger.info("File: " + form);

            for (UploadedFile uploadedFile: form.getFiles()) {
                MultipartFile file = uploadedFile.getFile();
                message += getNextFile(file) + "<br />";
            }
        }
        session.setAttribute("message", message);
        return "redirect:/files-main-page";
    }

    @RequestMapping(value = "/files-main-page", method = RequestMethod.GET)
    @ResponseBody
    public String goFilesMain(HttpSession session) {
        String message = (String) session.getAttribute("message");
        logger.info("Method goFilesMain: " + message);
        return message;
    }

    private String getNextFile(MultipartFile file) {
        String name = null;
        try {
            byte[] bytes = file.getBytes();
            name = file.getOriginalFilename();
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "tmpFiles");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            File uploadFile = new File(dir.getAbsolutePath() + File.separator + name);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
            stream.write(bytes);
            stream.flush();
            stream.close();

            logger.info("uploaded: " + uploadFile.getAbsolutePath());

            return "You successfully uploaded file= " + name;
        } catch (IOException e) {
            return "You failed to upload file" + name;
        }
    }
}
