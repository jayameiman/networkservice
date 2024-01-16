package id.co.bni.qris.domain.dto;

import id.co.bni.qris.domain.bo.BOSignOffRS;
import lombok.Builder;

@Builder
public class DTOSignOffRS{
    private BOSignOffRS QRSignOffRS;

    public BOSignOffRS getQRSignOffRS() {
        return QRSignOffRS;
    }

    public void setQRSignOffRS(BOSignOffRS qRSignOffRS) {
        QRSignOffRS = qRSignOffRS;
    }
}
