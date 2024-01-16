package id.co.bni.qris.domain.dto;
import id.co.bni.qris.domain.bo.BOEchoTestRS;
import lombok.Builder;

@Builder
public class DTOEchoTestRS {
    private BOEchoTestRS QREchoTestRS;

    public BOEchoTestRS getQREchoTestRS() {
        return QREchoTestRS;
    }

    public void setQREchoTestRS(BOEchoTestRS qREchoTestRS) {
        QREchoTestRS = qREchoTestRS;
    }
}
