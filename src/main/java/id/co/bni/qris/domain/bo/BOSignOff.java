package id.co.bni.qris.domain.bo;

import id.co.bni.qris.domain.dto.DTORqRs;

public class BOSignOff {
    public class QRSignOffRQ extends DTORqRs {
        private String identity;

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }
    }

    public class QRSignOffRS extends DTORqRs {
        private String responseCode;
        private String identity;

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
