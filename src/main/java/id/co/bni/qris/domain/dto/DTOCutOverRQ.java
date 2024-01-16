package id.co.bni.qris.domain.dto;
import id.co.bni.qris.domain.bo.BOCutOverRQ;
import lombok.Builder;

@Builder
public class DTOCutOverRQ {
    private BOCutOverRQ QRCutoverRQ;

    public BOCutOverRQ getQRCutoverRQ() {
        return QRCutoverRQ;
    }

    public void setQRCutoverRQ(BOCutOverRQ qRCutoverRQ) {
        QRCutoverRQ = qRCutoverRQ;
    }
}
