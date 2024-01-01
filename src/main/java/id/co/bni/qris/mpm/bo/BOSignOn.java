package id.co.bni.qris.mpm.bo;

import id.co.bni.qris.mpm.dto.DTORqRs;

public class BOSignOn {

    public class QRSignOnRQ extends DTORqRs {
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
    }

    public class QRSignOnRS extends DTORqRs {
        private String additionalData;
        private String responseCode;
        private String identity;

        public String getAdditionalData() {
            return additionalData;
        }

        public void setAdditionalData(String additionalData) {
            this.additionalData = additionalData;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }
    }
}
