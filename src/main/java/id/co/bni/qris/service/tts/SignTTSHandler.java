package id.co.bni.qris.service.tts;

import id.co.bni.qris.domain.bo.BOCutOver.QRCutoverRQ;
import id.co.bni.qris.domain.bo.BOEchoTest.QREchoTestRQ;
import id.co.bni.qris.domain.bo.BOSignOff.QRSignOffRQ;
import id.co.bni.qris.domain.bo.BOSignOn.QRSignOnRQ;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import id.co.bni.qris.domain.bo.BOSignOnRQ;
import id.co.bni.qris.domain.dto.DTOCutOver;
import id.co.bni.qris.domain.dto.DTOCutOver.QRCutoverRS;
import id.co.bni.qris.domain.dto.DTOEchoTest;
import id.co.bni.qris.domain.dto.DTOEchoTest.QREchoTestRS;
import id.co.bni.qris.domain.dto.DTOModel;
import id.co.bni.qris.domain.dto.DTOSignOff;
import id.co.bni.qris.domain.dto.DTOSignOff.QRSignOffRS;
import id.co.bni.qris.domain.dto.DTOSignOn;
import id.co.bni.qris.domain.dto.DTOSignOn.QRSignOnRS;
import id.co.bni.qris.fault.QrisException;
import id.co.bni.qris.service.SendServiceUtils;
import id.co.bni.qris.utils.ConfigUtils;
import id.co.bni.qris.utils.GeneratorUtils;

@Service
public class SignTTSHandler implements SignTTSService{
    public static Logger logger = LogManager.getLogger(SignTTSHandler.class);

    @Autowired
    GeneratorUtils generator;

    @Autowired
    ConfigUtils config;
    
    @Autowired
    SendServiceUtils serviceClient;

    @Value("${artajasa.url.tts}")
    private String urlTts;

    @Override
    public QRSignOnRS signOnAJCrossService(QRSignOnRQ request) throws Exception {
        DTOSignOn.QRSignOnRS response = new DTOSignOn.QRSignOnRS();
        DTOModel dtoModel = new DTOModel();
        String rc = "";

        try {
            String url = urlTts.concat("/signon");
            logger.info("[signOnAJCrossService] Endpoint : {}", url);

            JSONObject reqBody = new JSONObject();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);

            reqBody.put("msgType", request.getMsgType() );
            reqBody.put("transmissionDateTime", transmissionDateTime);
            reqBody.put("msgSTAN", generator.getRandomNumberString());
            reqBody.put("additionalData", request.getAdditionalData()); //BNI to Artajasa 6011002112N003602 || Artajasa to BNI 6011001112N003602
            reqBody.put("networkCode", request.getNetworkCode());
    
            JSONObject content = new JSONObject().put("QRSignOnRQ", reqBody);
    
            logger.info("request content: {}", content);
            String result = serviceClient.sendRequest(url, content.toString());
            logger.info("[signOnAJCrossService] response from Switcher : {}", result);

            JSONObject resp = new JSONObject(result).getJSONObject("QRSignOnRS");
            rc = resp.getString("responseCode");
            if(!rc.equals("00")){
                String message = "";
                throw new QrisException(message,"ARTAJASA",rc,null);
            }

            dtoModel.setQrSignOnRS(new Gson().fromJson(String.valueOf(resp), DTOSignOn.QRSignOnRS.class));
            response = dtoModel.getQrSignOnRS();
            

            //Create Request
            response.setMsgType(request.getMsgType());
            response.setTransmissionDateTime(generator.generateTransmissionDateTime());
            response.setAdditionalData(request.getAdditionalData());
            response.setMsgSTAN(generator.getRandomNumberString());
            response.setNetworkCode(request.getNetworkCode());
            response.setResponseCode(rc);

            logger.info("[signOnAJService] Create Request Body : "+ response,"");
            return response;

        }catch (Exception e){
            response.setResponseCode("9000");
            logger.error("[signOnAJService] | ERROR : {}",  e.getMessage());
            return response;
        }    
    }

    @Override
    public QRSignOffRS signOffACrossJService(QRSignOffRQ request) throws Exception {
        DTOSignOff.QRSignOffRS response = new DTOSignOff.QRSignOffRS();
        DTOModel dtoModel = new DTOModel();
        String rc = "";

        try {
            String url = urlTts.concat("/signoff");
            JSONObject reqBody = new JSONObject();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);

            reqBody.put("msgType", request.getMsgType() );
            reqBody.put("transmissionDateTime", transmissionDateTime);
            reqBody.put("msgSTAN", generator.getRandomNumberString());
            reqBody.put("networkCode", request.getNetworkCode());
            JSONObject content = new JSONObject().put("QRSignOffRQ", reqBody);

            logger.info("request content: {}", content);
            String result = serviceClient.sendRequest(url, content.toString());
            logger.info("[signOffAJCrossService] response from Switcher : {}", result);
            JSONObject resp = new JSONObject(result).getJSONObject("QRSignOffRS");

            rc = resp.getString("responseCode");
            if(!rc.equals("00")){
                String message = "";
                throw new QrisException(message,"ARTAJASA",rc,null);
            }

            dtoModel.setQrSignOffRS(new Gson().fromJson(String.valueOf(resp), DTOSignOff.QRSignOffRS.class));
            response = dtoModel.getQrSignOffRS();
            
            //Create response
            response.setMsgType(request.getMsgType());
            response.setTransmissionDateTime(generator.generateTransmissionDateTime());
            response.setMsgSTAN(generator.getRandomNumberString());
            response.setNetworkCode(request.getNetworkCode());
            response.setResponseCode(rc);

            logger.info("[signOffAJService] Create Request Body : {}", generator.convertObjectToString(response));
            return response;

        }catch (Exception e){
            response.setResponseCode("9000");
            logger.error("[signOffAJService] | ERROR : {}",  e.getMessage());
            return response;
        }    
    }

    @Override
    public QREchoTestRS echoTestAJCrossService(QREchoTestRQ request) throws Exception {
        DTOEchoTest.QREchoTestRS response = new DTOEchoTest.QREchoTestRS();
        DTOModel dtoModel = new DTOModel();
        String rc = "";

        try {
            String url = urlTts.concat("/echo");
            JSONObject reqBody = new JSONObject();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);

            reqBody.put("msgType", request.getMsgType() );
            reqBody.put("transmissionDateTime", transmissionDateTime);
            reqBody.put("msgSTAN", generator.getRandomNumberString());
            reqBody.put("networkCode", request.getNetworkCode());
            JSONObject content = new JSONObject().put("QREchoTestRQ", reqBody);

            logger.info("request content: {}", content);
            String result = serviceClient.sendRequest(url, content.toString());
            logger.info("[EchoTestAJCrossService] response from Switcher : {}", result);
            
            JSONObject resp = new JSONObject(result).getJSONObject("QREchoTestRS");
            rc = resp.getString("responseCode");
            
            if(!rc.equals("00")){
                String message = "";
                throw new QrisException(message,"ARTAJASA",rc,null);
            }
            
            dtoModel.setQrEchoTestRS(new Gson().fromJson(String.valueOf(resp), DTOEchoTest.QREchoTestRS.class));
            response = dtoModel.getQrEchoTestRS();
            
            
            //Create Request
            response.setMsgType(request.getMsgType());
            response.setTransmissionDateTime(generator.generateTransmissionDateTime());
            response.setMsgSTAN(generator.getRandomNumberString());
            response.setNetworkCode(request.getNetworkCode());
            response.setResponseCode(rc);
            
            logger.info("[echoTestAJService] Create Request Body : {}",generator.convertObjectToString(request));
            return response;

        }catch (Exception e){
            response.setResponseCode("9000");
            logger.error("[echoTestAJService] | ERROR : {}",  e.getMessage());
            return response;
        }    
    }

    @Override
    public QRCutoverRS cutoverAJCrossService(QRCutoverRQ request) throws Exception {
        DTOCutOver.QRCutoverRS response = new DTOCutOver.QRCutoverRS();
        String rc = "00";
        
        try {
            JSONObject reqBody = new JSONObject();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
            OffsetDateTime now = OffsetDateTime.now();
            Instant ins = now.toInstant();
            OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
            String transmissionDateTime = trxDateUTC.format(dtf);
            
            reqBody.put("msgType", request.getMsgType());
            reqBody.put("transmissionDateTime", transmissionDateTime);
            reqBody.put("msgSTAN", generator.getRandomNumberString());
            reqBody.put("networkCode", request.getNetworkCode());

            //Create Request
            response.setMsgType(request.getMsgType());
            response.setTransmissionDateTime(generator.generateTransmissionDateTime());
            response.setMsgSTAN(generator.getRandomNumberString());
            response.setNetworkCode(request.getNetworkCode());
            response.setProcessingDate(generator.generateTransmissionDateTimeMMdd());
            response.setResponseCode(rc);

            logger.info("[cutoverAJService] Create Request Body : "+ generator.convertObjectToString(response),"");
            return response;

        }catch (Exception e){
            response.setResponseCode("9000");
            logger.error("[cutoverAJService] | ERROR : {}",  e.getMessage());
            return response;
        }
    }
}
