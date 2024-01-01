package id.co.bni.qris.mpm.bo;


import id.co.bni.qris.mpm.dto.DTORqRs;

public class BOEchoTest extends DTORqRs {
    public class QREchoTestRQ extends DTORqRs {
        private String identity;

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }
    }

    public class QREchoTestRS extends DTORqRs {
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