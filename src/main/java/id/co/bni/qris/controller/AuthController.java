package id.co.bni.qris.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.bni.qris.domain.dto.auth.AuthRequestDTO;
import id.co.bni.qris.service.auth.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {
    public static Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @GetMapping("")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@Valid @RequestBody AuthRequestDTO registerDto) {
        logger.info("register controller");
        return authService.register(registerDto);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Valid @RequestBody AuthRequestDTO loginDto){
        logger.info("login controller");
        return authService.login(loginDto);
    }
}
