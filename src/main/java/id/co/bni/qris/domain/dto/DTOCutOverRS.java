package id.co.bni.qris.domain.dto;
import id.co.bni.qris.domain.bo.BOCutOverRS;
import lombok.Builder;

@Builder
public class DTOCutOverRS {
    private BOCutOverRS QRCutoverRS;

    public BOCutOverRS getQRCutoverRS() {
        return QRCutoverRS;
    }

    public void setQRCutoverRS(BOCutOverRS qRCutoverRS) {
        QRCutoverRS = qRCutoverRS;
    }
}
