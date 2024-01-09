package id.co.bni.qris.domain.dto;

public class DTOEchoTest {
    public class QREchoTestRQ extends DTORqRs {
    }

    public static class QREchoTestRS extends DTORqRs {
        private String responseCode;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }
    }
}
