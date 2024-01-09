package id.co.bni.qris.domain.bo;

import id.co.bni.qris.domain.dto.DTORqRs;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BOSignOnRQ extends DTORqRs {

    private String additionalData;
    private String identity;

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "BOSignOnRQ [additionalData=" + additionalData + ", identity=" + identity + "]";
    }

}