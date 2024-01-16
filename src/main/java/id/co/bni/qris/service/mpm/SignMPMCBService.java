package id.co.bni.qris.service.mpm;

import org.springframework.http.ResponseEntity;

import id.co.bni.qris.domain.bo.BOCutOverRQ;
import id.co.bni.qris.domain.bo.BOEchoTestRQ;
import id.co.bni.qris.domain.bo.BOSignOffRQ;
import id.co.bni.qris.domain.bo.BOSignOnRQ;

public interface SignMPMCBService {
    ResponseEntity<Object> signOnAJCrossService(BOSignOnRQ request) throws Exception;
    ResponseEntity<Object> signOffACrossJService(BOSignOffRQ request) throws Exception;
    ResponseEntity<Object> echoTestAJCrossService(BOEchoTestRQ request) throws Exception;
    ResponseEntity<Object> cutoverAJCrossService(BOCutOverRQ request) throws Exception;
}
