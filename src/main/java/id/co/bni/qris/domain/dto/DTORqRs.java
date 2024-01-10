package id.co.bni.qris.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class DTORqRs {
    @NotNull(message = "msgType can't be null")
    @NotEmpty(message = "msgType can't be empty")
    private String msgType;

    private String transmissionDateTime;
    
    private String msgSTAN;
    
    @NotNull(message = "networkCode can't be null")
    @NotEmpty(message = "networkCode can't be empty")
    private String networkCode;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTransmissionDateTime() {
        return transmissionDateTime;
    }

    public void setTransmissionDateTime(String transmissionDateTime) {
        this.transmissionDateTime = transmissionDateTime;
    }

    public String getMsgSTAN() {
        return msgSTAN;
    }

    public void setMsgSTAN(String msgSTAN) {
        this.msgSTAN = msgSTAN;
    }

    public String getNetworkCode() {
        return networkCode;
    }

    public void setNetworkCode(String networkCode) {
        this.networkCode = networkCode;
    }
}
