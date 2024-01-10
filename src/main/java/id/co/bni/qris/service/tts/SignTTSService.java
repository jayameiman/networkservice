package id.co.bni.qris.service.tts;

import id.co.bni.qris.domain.bo.BOCutOver;
import id.co.bni.qris.domain.bo.BOEchoTest;
import id.co.bni.qris.domain.bo.BOSignOff;
import id.co.bni.qris.domain.bo.BOSignOn;
import id.co.bni.qris.domain.dto.DTOCutOver;
import id.co.bni.qris.domain.dto.DTOEchoTest;
import id.co.bni.qris.domain.dto.DTOSignOff;
import id.co.bni.qris.domain.dto.DTOSignOn;

public interface SignTTSService {
    DTOSignOn.QRSignOnRS signOnAJCrossService(BOSignOn.QRSignOnRQ request) throws Exception;
    DTOSignOff.QRSignOffRS signOffACrossJService(BOSignOff.QRSignOffRQ request) throws Exception;
    DTOEchoTest.QREchoTestRS echoTestAJCrossService(BOEchoTest.QREchoTestRQ request) throws Exception;
    DTOCutOver.QRCutoverRS cutoverAJCrossService(BOCutOver.QRCutoverRQ request) throws Exception;

}
