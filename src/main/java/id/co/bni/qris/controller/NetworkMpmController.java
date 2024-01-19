package id.co.bni.qris.controller;

import id.co.bni.qris.service.mpm.SignMPMCBHandler;
import id.co.bni.qris.service.user.UserService;
import id.co.bni.qris.domain.bo.BOCutOverRQ;
import id.co.bni.qris.domain.bo.BOEchoTestRQ;
import id.co.bni.qris.domain.bo.BOSignOffRQ;
import id.co.bni.qris.domain.bo.BOSignOnRQ;
import id.co.bni.qris.service.auth.JwtService;
import id.co.bni.qris.utils.GeneratorUtils;
import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/qris/mpm")
public class NetworkMpmController {
    public static Logger logger = LogManager.getLogger(NetworkMpmController.class);

    @Autowired
    SignMPMCBHandler signMPMCBService;

    @Autowired
    GeneratorUtils generator;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;

    @PostMapping(value = "/signOn", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signonController(@Valid @RequestBody BOSignOnRQ request) throws Exception {
        logger.info("from controller: mpm sign on started");
        
        return signMPMCBService.signOnAJCrossService(request);
    }

    @PostMapping(value = "/signOff",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signOffController(@Valid @RequestBody BOSignOffRQ request) throws Exception {
        logger.info("from controller: mpm sign off started");
        
        return signMPMCBService.signOffACrossJService(request);
    }

    @PostMapping(value = "/echoTest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> echoTestController(@Valid @RequestBody BOEchoTestRQ request) throws Exception {
        logger.info("from controller: mpm echo test started");        
        
        return signMPMCBService.echoTestAJCrossService(request);
    }
    
    @PostMapping(value = "/cutOver",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cutOverController(@Valid @RequestBody BOCutOverRQ request) throws Exception{
        logger.info("from controller: mpm cut over started");

        return signMPMCBService.cutoverAJCrossService(request); 
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
