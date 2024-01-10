package id.co.bni.qris.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import id.co.bni.qris.domain.bo.BOCutOver;
import id.co.bni.qris.domain.bo.BOEchoTest;
import id.co.bni.qris.domain.bo.BOModel;
import id.co.bni.qris.domain.bo.BOSignOff;
import id.co.bni.qris.domain.bo.BOSignOn;
import id.co.bni.qris.domain.dto.DTOCutOver;
import id.co.bni.qris.domain.dto.DTOEchoTest;
import id.co.bni.qris.domain.dto.DTOSignOff;
import id.co.bni.qris.domain.dto.DTOSignOn;
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
    public Object signonService(@RequestBody String request) throws Exception {
        JSONObject requestBody = new JSONObject(request).getJSONObject("QRSignOnRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQRSignOnRQ(new Gson().fromJson(requestBody.toString(), BOSignOn.QRSignOnRQ.class));

        DTOSignOn.QRSignOnRS result = signTTSHandler.signOnAJCrossService(boModel.getBoQRSignOnRQ());
        JSONObject response = new JSONObject();
        response.put("QRSignOnRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);    
    }

    @PostMapping(value = "/signOff",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object signOffService(@RequestBody String request) throws Exception {
        JSONObject requestBody = new JSONObject(request).getJSONObject("QRSignOffRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQRSignOffRQ(new Gson().fromJson(requestBody.toString(), BOSignOff.QRSignOffRQ.class));

        DTOSignOff.QRSignOffRS result = signTTSHandler.signOffACrossJService(boModel.getBoQRSignOffRQ());
        JSONObject response = new JSONObject();
        response.put("QRSignOffRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
    }

    @PostMapping(value = "/echoTest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> echoTestService(@RequestBody String request) throws Exception {
        JSONObject requestBody = new JSONObject(request).getJSONObject("QREchoTestRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQREchoTestRQ(new Gson().fromJson(requestBody.toString(), BOEchoTest.QREchoTestRQ.class));

        DTOEchoTest.QREchoTestRS result = signTTSHandler.echoTestAJCrossService(boModel.getBoQREchoTestRQ());
        JSONObject response = new JSONObject();
        response.put("QREchoTestRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
    }

    @PostMapping(value = "/cutOver",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> cutOverService(@RequestBody String request) throws Exception{
        JSONObject requestBody = new JSONObject(request).getJSONObject("QRCutoverRQ");
        BOModel boModel = new BOModel();
        logger.info("Original Body Request : {} ", requestBody.toString());

        boModel.setBoQRCutoverRQ(new Gson().fromJson(requestBody.toString(), BOCutOver.QRCutoverRQ.class));

        DTOCutOver.QRCutoverRS result = signTTSHandler.cutoverAJCrossService(boModel.getBoQRCutoverRQ());
        JSONObject response = new JSONObject();
        response.put("QRCutoverRS", result);

        return new ResponseEntity<Object>(response.toMap(), HttpStatus.OK);
    }
}
