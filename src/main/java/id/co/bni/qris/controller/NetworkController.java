package id.co.bni.qris.controller;

import com.google.gson.Gson;

import id.co.bni.qris.service.auth.UserService;
import id.co.bni.qris.service.mpm.SignMPMCBService;
import id.co.bni.qris.domain.bo.*;
import id.co.bni.qris.domain.bo.BOSignOn.QRSignOnRQ;
import id.co.bni.qris.domain.dto.*;
import id.co.bni.qris.service.auth.JwtService;
import id.co.bni.qris.utils.GeneratorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/qris")
public class NetworkController {
    public static Logger logger = LogManager.getLogger(NetworkController.class);

    @Autowired
    SignMPMCBService signMPMCBService;

    @Autowired
    GeneratorUtils generator;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;

    @GetMapping("")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/mpm/signOn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signonService(@RequestBody BOSignOnRQ request) throws Exception {
    // public ResponseEntity<Object> signonService() throws Exception {
        logger.info("signon controller");
        
        return signMPMCBService.signOnAJCrossService(request);
        // return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping(value = "/mpm/signOff",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object signOffService(@RequestBody String request) throws Exception {
        JSONObject requestBody = new JSONObject(request).getJSONObject("QRSignOffRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQRSignOffRQ(new Gson().fromJson(requestBody.toString(), BOSignOff.QRSignOffRQ.class));

        DTOSignOff.QRSignOffRS result = signMPMCBService.signOffACrossJService(boModel.getBoQRSignOffRQ());
        JSONObject response = new JSONObject();
        response.put("QRSignOffRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
    }

    @PostMapping(value = "/mpm/echoTest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> echoTestService(@RequestBody @NotNull String request) throws Exception {
        JSONObject requestBody = new JSONObject(request).getJSONObject("QREchoTestRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQREchoTestRQ(new Gson().fromJson(requestBody.toString(), BOEchoTest.QREchoTestRQ.class));

        DTOEchoTest.QREchoTestRS result = signMPMCBService.echoTestAJCrossService(boModel.getBoQREchoTestRQ());
        JSONObject response = new JSONObject();
        response.put("QREchoTestRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
    }

    @PostMapping(value = "/mpm/cutOver",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> cutOverService(@RequestBody @NotNull String request) throws Exception{
        JSONObject requestBody = new JSONObject(request).getJSONObject("QRCutoverRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQRCutoverRQ(new Gson().fromJson(requestBody.toString(), BOCutOver.QRCutoverRQ.class));

        DTOCutOver.QRCutoverRS result = signMPMCBService.cutoverAJCrossService(boModel.getBoQRCutoverRQ());
        JSONObject response = new JSONObject();
        response.put("QRCutoverRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
