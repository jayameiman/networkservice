package id.co.bni.qris.domain.dto;

import id.co.bni.qris.domain.bo.BOSignOnRS;
import lombok.Builder;

@Builder
public class DTOSignOnRS {
    
    private BOSignOnRS QRSignOnRS;

    public BOSignOnRS getQRSignOnRS() {
        return QRSignOnRS;
    }

    public void setQRSignOnRS(BOSignOnRS qRSignOnRS) {
        QRSignOnRS = qRSignOnRS;
    }

}
