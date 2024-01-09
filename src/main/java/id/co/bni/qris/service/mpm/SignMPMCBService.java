package id.co.bni.qris.service.mpm;

import org.springframework.http.ResponseEntity;

import id.co.bni.qris.domain.bo.BOCutOver;
import id.co.bni.qris.domain.bo.BOEchoTest;
import id.co.bni.qris.domain.bo.BOSignOff;
import id.co.bni.qris.domain.bo.BOSignOnRQ;
import id.co.bni.qris.domain.dto.DTOCutOver;
import id.co.bni.qris.domain.dto.DTOEchoTest;
import id.co.bni.qris.domain.dto.DTOSignOff;

public interface SignMPMCBService {
    ResponseEntity<Object> signOnAJCrossService(BOSignOnRQ request) throws Exception;
    DTOSignOff.QRSignOffRS signOffACrossJService(BOSignOff.QRSignOffRQ request) throws Exception;
    DTOEchoTest.QREchoTestRS echoTestAJCrossService(BOEchoTest.QREchoTestRQ request) throws Exception;
    DTOCutOver.QRCutoverRS cutoverAJCrossService(BOCutOver.QRCutoverRQ request) throws Exception;

}
