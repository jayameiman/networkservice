package id.co.bni.qris.mpm.dto;

public class DTOSignOn {
    public class QRSignOnRQ extends DTORqRs {
        private String additionalData;

        public String getAdditionalData() {
            return additionalData;
        }

        public void setAdditionalData(String additionalData) {
            this.additionalData = additionalData;
        }
    }

    public static class QRSignOnRS extends DTORqRs {
        private String additionalData;
        private String responseCode;

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
    }
}
