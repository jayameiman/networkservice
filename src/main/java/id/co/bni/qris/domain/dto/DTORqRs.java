package id.co.bni.qris.domain.dto;

public class DTORqRs {
    private String msgType;
    private String transmissionDateTime;
    private String msgSTAN;
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
