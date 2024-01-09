package id.co.bni.qris.domain.bo;

import id.co.bni.qris.domain.dto.DTORqRs;

public class BOCutOver {
    public class QRCutoverRQ extends DTORqRs{
        private String processingDate;
        private String identity;
        public String getProcessingDate() {
            return processingDate;
        }
        public void setProcessingDate(String processingDate) {
            this.processingDate = processingDate;
        }
        public String getIdentity() {
            return identity;
        }
        public void setIdentity(String identity) {
            this.identity = identity;
        }
    }

    public class QRCutoverRS extends DTORqRs {
        private String processingDate;
        private String responseCode;

        public String getProcessingDate() {
            return processingDate;
        }

        public void setProcessingDate(String processingDate) {
            this.processingDate = processingDate;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }
    }
}
