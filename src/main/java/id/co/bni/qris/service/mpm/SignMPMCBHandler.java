package id.co.bni.qris.service.mpm;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.co.bni.qris.domain.bo.BOCutOverRQ;
import id.co.bni.qris.domain.bo.BOCutOverRS;
import id.co.bni.qris.domain.bo.BOEchoTestRQ;
import id.co.bni.qris.domain.bo.BOEchoTestRS;
import id.co.bni.qris.domain.bo.BOSignOffRQ;
import id.co.bni.qris.domain.bo.BOSignOffRS;
import id.co.bni.qris.domain.bo.BOSignOnRQ;
import id.co.bni.qris.domain.bo.BOSignOnRS;
import id.co.bni.qris.domain.dto.DTOCutOverRQ;
import id.co.bni.qris.domain.dto.DTOCutOverRS;
import id.co.bni.qris.domain.dto.DTOEchoTestRQ;
import id.co.bni.qris.domain.dto.DTOEchoTestRS;
import id.co.bni.qris.domain.dto.DTOSignOffRQ;
import id.co.bni.qris.domain.dto.DTOSignOffRS;
import id.co.bni.qris.domain.dto.DTOSignOnRQ;
import id.co.bni.qris.domain.dto.DTOSignOnRS;
import id.co.bni.qris.service.SendServiceUtils;
import id.co.bni.qris.utils.ConfigUtils;
import id.co.bni.qris.utils.GeneratorUtils;
import id.co.bni.qris.utils.RestUtil;

@Service
public class SignMPMCBHandler implements SignMPMCBService {
    public static Logger logger = LogManager.getLogger(SignMPMCBHandler.class);

    @Autowired
    GeneratorUtils generator;

    @Autowired
    ConfigUtils config;
    
    @Autowired
    SendServiceUtils serviceClient;

    @Value("${artajasa.url.cb}")
    private String urlCb;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> signOnAJCrossService(BOSignOnRQ request) throws Exception {
        BOSignOnRS boSignOnRS = BOSignOnRS.builder().build();
        
        try {
            String url = urlCb.concat("/signon");
            logger.info("[signOnAJCrossService] Endpoint : {}", url);
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);
            
            request.setTransmissionDateTime(transmissionDateTime);
            request.setMsgSTAN(generator.getRandomNumberString());
            
            DTOSignOnRQ dtoSignOnRQ = DTOSignOnRQ.builder().QRSignOnRQ(request).build();

            logger.info("request content: {}", RestUtil.toJson(dtoSignOnRQ));
            String result = serviceClient.sendRequest(url, RestUtil.toJson(dtoSignOnRQ).toString());
            logger.info("[signOnAJCrossService] response from Switcher : {}", result);

            JSONObject resp = new JSONObject(result).getJSONObject("QRSignOnRS");
            
            String rc = resp.getString("responseCode");
            if(!rc.equals("00")){
                String message = "Error while send request to AJ, rc=" + rc;
                throw new Exception(message);
            }

            DTOSignOnRS dtoSignOnRS = RestUtil.jsonToObject(result, DTOSignOnRS.class);
            boSignOnRS.setAdditionalData(dtoSignOnRS.getQRSignOnRS().getAdditionalData());
            boSignOnRS.setMsgSTAN(dtoSignOnRS.getQRSignOnRS().getMsgSTAN());
            boSignOnRS.setMsgType(dtoSignOnRS.getQRSignOnRS().getMsgType());
            boSignOnRS.setNetworkCode(dtoSignOnRS.getQRSignOnRS().getNetworkCode());
            boSignOnRS.setTransmissionDateTime(dtoSignOnRS.getQRSignOnRS().getTransmissionDateTime());
            boSignOnRS.setResponseCode(dtoSignOnRS.getQRSignOnRS().getResponseCode());

            logger.info("[signOnAJService] Create Request Body : {}", RestUtil.toJson(boSignOnRS));
        }catch (Exception e){
            logger.error("[signOnAJService] | ERROR : {}",  e.getMessage());
        }

        return new ResponseEntity<>(boSignOnRS, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Object> signOffACrossJService(BOSignOffRQ request) throws Exception {
        BOSignOffRS boSignOffRS = BOSignOffRS.builder().build();
        
        try {
            String url = urlCb.concat("/signoff");
            logger.info("[signOffAJCrossService] Endpoint : {}", url);
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);
            
            request.setTransmissionDateTime(transmissionDateTime);
            request.setMsgSTAN(generator.getRandomNumberString());
            
            DTOSignOffRQ dtoSignOffRQ = DTOSignOffRQ.builder().QRSignOffRQ(request).build();
            
            logger.info("request content: {}", RestUtil.toJson(dtoSignOffRQ));
            String result = serviceClient.sendRequest(url, RestUtil.toJson(dtoSignOffRQ).toString());
            logger.info("[signOffAJCrossService] response from Switcher : {}", result);
            
            JSONObject resp = new JSONObject(result).getJSONObject("QRSignOffRS");
            
            String rc = resp.getString("responseCode");
            if(!rc.equals("00")){
                String message = "Error while send request to AJ, rc=" + rc;
                throw new Exception(message);
            }
            
            DTOSignOffRS dtoSignOffRS = RestUtil.jsonToObject(result, DTOSignOffRS.class);
            boSignOffRS.setMsgSTAN(dtoSignOffRS.getQRSignOffRS().getMsgSTAN());
            boSignOffRS.setMsgType(dtoSignOffRS.getQRSignOffRS().getMsgType());
            boSignOffRS.setNetworkCode(dtoSignOffRS.getQRSignOffRS().getNetworkCode());
            boSignOffRS.setTransmissionDateTime(dtoSignOffRS.getQRSignOffRS().getTransmissionDateTime());
            boSignOffRS.setResponseCode(dtoSignOffRS.getQRSignOffRS().getResponseCode());
            
            logger.info("[signOnAJService] Create Request Body : {}", RestUtil.toJson(boSignOffRS));
        }catch (Exception e){
            logger.error("[signOffAJService] | ERROR : {}",  e.getMessage());
        }
        
        return new ResponseEntity<>(boSignOffRS, HttpStatusCode.valueOf(200));
    }
    
    @Override
    public ResponseEntity<Object> echoTestAJCrossService(BOEchoTestRQ request) throws Exception {
        BOEchoTestRS boEchoTestRS = BOEchoTestRS.builder().build();
        
        try {
            String url = urlCb.concat("/echo");
            logger.info("[signOffAJCrossService] Endpoint : {}", url);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);

            request.setTransmissionDateTime(transmissionDateTime);
            request.setMsgSTAN(generator.getRandomNumberString());

            DTOEchoTestRQ dtoEchoTestRQ = DTOEchoTestRQ.builder().QREchoTestRQ(request).build();
            
            logger.info("request content: {}", RestUtil.toJson(dtoEchoTestRQ));
            String result = serviceClient.sendRequest(url, RestUtil.toJson(dtoEchoTestRQ));
            logger.info("[EchoTestAJCrossService] response from Switcher : {}", result);
            
            JSONObject resp = new JSONObject(result).getJSONObject("QREchoTestRS");
            String rc = resp.getString("responseCode");
            
            if(!rc.equals("00")){
                String message = "Error while send request to AJ, rc=" + rc;
                throw new Exception(message);
            }

            DTOEchoTestRS dtoEchoTestRS = RestUtil.jsonToObject(result, DTOEchoTestRS.class);
            boEchoTestRS.setMsgSTAN(dtoEchoTestRS.getQREchoTestRS().getMsgSTAN());
            boEchoTestRS.setMsgType(dtoEchoTestRS.getQREchoTestRS().getMsgType());
            boEchoTestRS.setNetworkCode(dtoEchoTestRS.getQREchoTestRS().getNetworkCode());
            boEchoTestRS.setResponseCode(dtoEchoTestRS.getQREchoTestRS().getResponseCode());
            boEchoTestRS.setTransmissionDateTime(dtoEchoTestRS.getQREchoTestRS().getTransmissionDateTime());

            logger.info("[echoTestAJService] Create Request Body : {}", RestUtil.toJson(boEchoTestRS));
        }catch (Exception e){
            logger.error("[echoTestAJService] | ERROR : {}",  e.getMessage());
        }

        return new ResponseEntity<>(boEchoTestRS, HttpStatusCode.valueOf(200));
    }
    
    @Override
    public ResponseEntity<Object> cutoverAJCrossService(BOCutOverRQ request) throws Exception {
        BOCutOverRS boCutOverRS = BOCutOverRS.builder().build();
        
        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);
            
            request.setTransmissionDateTime(transmissionDateTime);
            request.setMsgSTAN(generator.getRandomNumberString());
            
            boCutOverRS.setMsgType(request.getMsgType());
            boCutOverRS.setMsgSTAN(generator.getRandomNumberString());
            boCutOverRS.setTransmissionDateTime(transmissionDateTime);
            boCutOverRS.setNetworkCode(request.getNetworkCode());
            boCutOverRS.setProcessingDate(request.getProcessingDate());
            boCutOverRS.setResponseCode("00");

            logger.info("[cutoverAJService]");
        }catch (Exception e){
            logger.error("[cutoverAJService] | ERROR : {}",  e.getMessage());
        }

        return new ResponseEntity<>(boCutOverRS, HttpStatusCode.valueOf(200));
    }
}
