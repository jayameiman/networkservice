package id.co.bni.qris.domain.dto;

import id.co.bni.qris.domain.bo.BOSignOffRQ;
import lombok.Builder;

@Builder
public class DTOSignOffRQ {
    private BOSignOffRQ QRSignOffRQ;

    public BOSignOffRQ getQRSignOffRQ() {
        return QRSignOffRQ;
    }

    public void setQRSignOffRQ(BOSignOffRQ qRSignOffRQ) {
        QRSignOffRQ = qRSignOffRQ;
    }
}
