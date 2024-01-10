package id.co.bni.qris.domain.dto;

import id.co.bni.qris.domain.bo.BOSignOnRQ;
import lombok.Builder;

@Builder
public class DTOSignOnRQ {
    private BOSignOnRQ QRSignOnRQ;

    public BOSignOnRQ getQRSignOnRQ() {
        return QRSignOnRQ;
    }

    public void setQRSignOnRQ(BOSignOnRQ qRSignOnRQ) {
        QRSignOnRQ = qRSignOnRQ;
    }
}
