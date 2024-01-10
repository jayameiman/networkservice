package id.co.bni.qris.service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class SendServiceUtils {
    private static final Logger logger = LogManager.getLogger(SendServiceUtils.class);

    public String sendRequest(String url, String request) {
		String response = "";
		WebClient client = WebClient.create();
		OffsetDateTime now = OffsetDateTime.now();
        Instant ins = now.toInstant();
        OffsetDateTime trxDateUTC = ins.atOffset(ZoneOffset.UTC);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, dd LLL yyyy HH:mm:ss");
        String dateHeader = trxDateUTC.format(dtf) + " GMT";

		try {
			response = client.post()
				.uri(url)
				.header("Date", dateHeader)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(request)
				.retrieve()
				.bodyToMono(String.class)
				.block();

		} catch(WebClientResponseException rawErrResp) {
			logger.info("RAW BODY RESP: {} | {} | {}", url, request, rawErrResp.getResponseBodyAsString());
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("ERROR: {}", e.toString());			
		}

		return response;
	}
}