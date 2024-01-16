package id.co.bni.qris.domain.dto;
import id.co.bni.qris.domain.bo.BOEchoTestRQ;
import lombok.Builder;

@Builder
public class DTOEchoTestRQ {
    BOEchoTestRQ QREchoTestRQ;

    public BOEchoTestRQ getQREchoTestRQ() {
        return QREchoTestRQ;
    }

    public void setQREchoTestRQ(BOEchoTestRQ qREchoTestRQ) {
        QREchoTestRQ = qREchoTestRQ;
    }
}
