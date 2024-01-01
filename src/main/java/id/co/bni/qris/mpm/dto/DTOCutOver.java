package id.co.bni.qris.mpm.dto;

public class DTOCutOver {
    public class QRCutoverRQ extends DTORqRs{
        private String processingDate;

        public String getProcessingDate() {
            return processingDate;
        }

        public void setProcessingDate(String processingDate) {
            this.processingDate = processingDate;
        }
    }

    public static class QRCutoverRS extends DTORqRs {
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
