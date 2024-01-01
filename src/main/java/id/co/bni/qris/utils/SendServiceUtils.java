package id.co.bni.qris.utils;

import com.google.gson.JsonObject;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SendServiceUtils {
    public static Logger logger = LogManager.getLogger(SendServiceUtils.class);
    @Autowired
    GeneratorUtils generator;
    public String sendRequest(String url, String requestBody) {
        long start = generator.startTime();
        try {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Content-Type", jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
                    .addHeader("Date", generator.generateGMTDatetime())
                    .build();

            logger.info("[sendRequest] Outgoing request to : {} Body : {} process done in 0 ms", request, requestBody);

            Call call = client.newCall(request);
            Response response = call.execute();

            assert response.body() != null;
            String rsp = response.body().string();
            int responseCode = response.code();
            response.close();

            logger.info(" [sendRequest] - Response Code : {} Incoming response : {} process done in {} ms", responseCode,generator.convertToOneLine(rsp), (response.receivedResponseAtMillis() - response.sentRequestAtMillis()));

            return rsp;
        } catch (Exception e) {
            JsonObject error = new JsonObject();
            error.addProperty("responseCode","9000");
            error.addProperty("message", e.getMessage());
            logger.error("[sendRequest] - ERROR {} process done in {} ms : ", e.getMessage(), generator.elapsedTime(start));
            return error.toString();
        }
    }
}
