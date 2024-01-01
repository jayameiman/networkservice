package id.co.bni.qris.mpm.controller;

import com.google.gson.Gson;
import id.co.bni.qris.mpm.bo.*;
import id.co.bni.qris.mpm.dto.*;
import id.co.bni.qris.mpm.dto.auth.AuthRequestDTO;
import id.co.bni.qris.mpm.dto.auth.JwtResponseDTO;
import id.co.bni.qris.mpm.dto.auth.UserRequest;
import id.co.bni.qris.mpm.dto.auth.UserResponse;
import id.co.bni.qris.mpm.service.auth.UserService;
import id.co.bni.qris.mpm.service.mpm.SignMPMCBService;
import id.co.bni.qris.mpm.service.auth.JwtService;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/qris", method = RequestMethod.POST)
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

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/mpm/signOn",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signonService(@RequestBody @NotNull String request) throws Exception {
        JSONObject requestBody = new JSONObject(request).getJSONObject("QRSignOnRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQRSignOnRQ(new Gson().fromJson(requestBody.toString(), BOSignOn.QRSignOnRQ.class));

        DTOSignOn.QRSignOnRS result = signMPMCBService.signOnAJCrossService(boModel.getBoQRSignOnRQ());
        JSONObject response = new JSONObject();
        response.put("QRSignOnRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
    }

    @PostMapping(value = "/mpm/signOff",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object signOffService(@RequestBody @NotNull String request) throws Exception {
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

    @PostMapping(value = "/save")
    public ResponseEntity saveUser(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.saveUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        try {
            List<UserResponse> userResponses = userService.getAllUser();
            return ResponseEntity.ok(userResponses);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile() {
        try {
            UserResponse userResponse = userService.getUser();
            return ResponseEntity.ok().body(userResponse);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
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
