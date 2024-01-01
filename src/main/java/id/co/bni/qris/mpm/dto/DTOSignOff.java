package id.co.bni.qris.mpm.dto;

public class DTOSignOff {
    public class QRSignOffRQ extends DTORqRs {

    }

    public static class QRSignOffRS extends DTORqRs {
        private String responseCode;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }
    }
}
