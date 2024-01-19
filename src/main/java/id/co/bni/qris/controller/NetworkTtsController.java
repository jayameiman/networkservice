package id.co.bni.qris.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.bni.qris.domain.bo.BOCutOverRQ;
import id.co.bni.qris.domain.bo.BOEchoTestRQ;
import id.co.bni.qris.domain.bo.BOSignOffRQ;
import id.co.bni.qris.domain.bo.BOSignOnRQ;
import id.co.bni.qris.service.tts.SignTTSHandler;

@RestController
@RequestMapping(value = "/v1/qris/tts")
public class NetworkTtsController {
    public static Logger logger = LogManager.getLogger(NetworkTtsController.class);

    @Autowired
    SignTTSHandler signTTSHandler;
    
    @PostMapping(value = "/signOn", 
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signonController(@RequestBody BOSignOnRQ request) throws Exception {
        logger.info("from controller: tts sign on started");
        
        return signTTSHandler.signOnAJCrossService(request);
    }
    
    @PostMapping(value = "/signOff",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signOffController(@RequestBody BOSignOffRQ request) throws Exception {
        logger.info("from controller: tts sign off started");
        
        return signTTSHandler.signOffACrossJService(request);
    }
    
    @PostMapping(value = "/echoTest",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> echoTestController(@RequestBody BOEchoTestRQ request) throws Exception {
        logger.info("from controller: tts sign echo test started");
        
        return signTTSHandler.echoTestAJCrossService(request);
    }
    
    @PostMapping(value = "/cutOver",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cutOverController(@RequestBody BOCutOverRQ request) throws Exception{
        logger.info("from controller: tts sign cut over started");
        
        return signTTSHandler.cutoverAJCrossService(request);
    }
}
