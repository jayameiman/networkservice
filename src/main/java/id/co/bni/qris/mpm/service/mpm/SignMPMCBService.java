package id.co.bni.qris.mpm.service.mpm;

import id.co.bni.qris.mpm.bo.BOCutOver;
import id.co.bni.qris.mpm.bo.BOEchoTest;
import id.co.bni.qris.mpm.bo.BOSignOff;
import id.co.bni.qris.mpm.bo.BOSignOn;
import id.co.bni.qris.mpm.dto.DTOCutOver;
import id.co.bni.qris.mpm.dto.DTOEchoTest;
import id.co.bni.qris.mpm.dto.DTOSignOff;
import id.co.bni.qris.mpm.dto.DTOSignOn;

public interface SignMPMCBService {
    DTOSignOn.QRSignOnRS signOnAJCrossService(BOSignOn.QRSignOnRQ request) throws Exception;
    DTOSignOff.QRSignOffRS signOffACrossJService(BOSignOff.QRSignOffRQ request) throws Exception;
    DTOEchoTest.QREchoTestRS echoTestAJCrossService(BOEchoTest.QREchoTestRQ request) throws Exception;
    DTOCutOver.QRCutoverRS cutoverAJCrossService(BOCutOver.QRCutoverRQ request) throws Exception;

}
