package id.co.bni.qris.controller;

import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.bni.qris.service.user.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    public static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;
    
    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers() {
        logger.info("from controller: get all users started");

        return userService.getAllUser();
    }

    @PostMapping(value = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserProfile() {
        logger.info("from controller: get profile started");

        return userService.getUser();
    }

}
