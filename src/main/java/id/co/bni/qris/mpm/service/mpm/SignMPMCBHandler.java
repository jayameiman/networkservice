package id.co.bni.qris.mpm.service.mpm;

import com.google.gson.Gson;
import id.co.bni.qris.fault.QrisException;
import id.co.bni.qris.mpm.dto.*;
import id.co.bni.qris.mpm.bo.BOCutOver;
import id.co.bni.qris.mpm.bo.BOEchoTest;
import id.co.bni.qris.mpm.bo.BOSignOff;
import id.co.bni.qris.mpm.bo.BOSignOn;
import id.co.bni.qris.utils.ConfigUtils;
import id.co.bni.qris.utils.GeneratorUtils;
import id.co.bni.qris.utils.SendServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignMPMCBHandler implements SignMPMCBService {
    public static Logger logger = LogManager.getLogger(SignMPMCBHandler.class);
    @Autowired
    GeneratorUtils generator;
    @Autowired
    ConfigUtils config;
    @Autowired
    SendServiceUtils serviceClient;

    @Override
    public DTOSignOn.QRSignOnRS signOnAJCrossService(BOSignOn.QRSignOnRQ request) throws Exception {
        DTOSignOn.QRSignOnRS response = new DTOSignOn.QRSignOnRS();
        DTOModel dtoModel = new DTOModel();
        String rc = "";

        try {
            String url = config.endpoint.concat("/signon");
            logger.info("[signOnAJCrossService] Endpoint : {}", url);

            if(request.getIdentity().equals("BNI")){
                JSONObject content = new JSONObject().put("QRSignOnRQ", request);
                String result = serviceClient.sendRequest(url, content.toString());
                logger.info("[signOnAJCrossService] response from Switcher : {}", result);

                JSONObject resp = new JSONObject(result).getJSONObject("QRSignOnRS");
                rc = resp.getString("responseCode");
                if(!rc.equals("00")){
                    String message = "";
                    throw new QrisException(message,"ARTAJASA",rc,null);
                }

                dtoModel.setQrSignOnRS(new Gson().fromJson(String.valueOf(resp), DTOSignOn.QRSignOnRS.class));
                return response = dtoModel.getQrSignOnRS();
            }

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
    public DTOSignOff.QRSignOffRS signOffACrossJService(BOSignOff.QRSignOffRQ request) throws Exception {
        DTOSignOff.QRSignOffRS response = new DTOSignOff.QRSignOffRS();
        DTOModel dtoModel = new DTOModel();
        String rc = "";

        try {
            String url = config.endpoint.concat("/signoff");
            if(request.getIdentity().equals("BNI")){

                JSONObject content = new JSONObject().put("QRSignOffRQ", request);
                String result = serviceClient.sendRequest(url, content.toString());
                JSONObject resp = new JSONObject(result).getJSONObject("QRSignOffRS");

                rc = resp.getString("responseCode");
                if(!rc.equals("00")){
                    String message = "";
                    throw new QrisException(message,"ARTAJASA",rc,null);
                }

                dtoModel.setQrSignOffRS(new Gson().fromJson(String.valueOf(resp), DTOSignOff.QRSignOffRS.class));
                return response = dtoModel.getQrSignOffRS();
            }

            //Create Request
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
    public DTOEchoTest.QREchoTestRS echoTestAJCrossService(BOEchoTest.QREchoTestRQ request) throws Exception {
        DTOEchoTest.QREchoTestRS response = new DTOEchoTest.QREchoTestRS();
        DTOModel dtoModel = new DTOModel();
        String rc = "";

        try {

            String url = config.endpoint.concat("/echo");
            if(request.getIdentity().equals("BNI")){
                JSONObject content = new JSONObject().put("QREchoTestRQ", request);

                String result = serviceClient.sendRequest(url, content.toString());
                JSONObject resp = new JSONObject(result).getJSONObject("QRSignOnRS");
                rc = resp.getString("responseCode");

                if(!rc.equals("00")){
                    String message = "";
                    throw new QrisException(message,"ARTAJASA",rc,null);
                }

                dtoModel.setQrEchoTestRS(new Gson().fromJson(String.valueOf(resp), DTOEchoTest.QREchoTestRS.class));
                return response = dtoModel.getQrEchoTestRS();
            }

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
    public DTOCutOver.QRCutoverRS cutoverAJCrossService(BOCutOver.QRCutoverRQ request) throws Exception {
        DTOCutOver.QRCutoverRS response = new DTOCutOver.QRCutoverRS();
        String rc = "";

        try {
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
