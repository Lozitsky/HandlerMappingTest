package com.kirilo.springMVC.controllers;


import com.kirilo.springMVC.models.User;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserRestController {
    private static final Logger logger = Logger.getLogger(UserRestController.class);

//    http://localhost:8080/SpringMVC/get-json-user?name=asdasdsad
/*    @RequestMapping(value = "/get-json-user", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    private User getUser(@RequestParam("name") String name){
        logger.info(name);
        User user = new User();
        user.setName(name);
        return user;
    }*/

//http://localhost:8080/SpringMVC/get-json-user/asd55a4sdsad/false
    @RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    private User getUser(@PathVariable("name") String name, @PathVariable("admin") Boolean isAdmin) {
        logger.info(name);
        User user = new User();
        user.setName(name);
        user.setAdmin(isAdmin);
        return user;
    }

    @RequestMapping(value = "/get-xml-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    private User getUserXML(@PathVariable("name") String name, @PathVariable("admin") boolean isAdmin) {
        logger.info(name);
        User user = new User();
        user.setName(name);
        user.setAdmin(isAdmin);
        return user;
    }

    @RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
    private ResponseEntity<String> setJsonUser(@RequestBody User user) {
        logger.info("method setJsonUser: " + user.getName());
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/put-xml-user", method = RequestMethod.POST, consumes = "application/xml")
    private ResponseEntity<String> setXMLUser(@RequestBody User user) {
        logger.info("method setXMLUser: " + user.getName());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
