package id.co.bni.qris.domain.bo;

import id.co.bni.qris.domain.dto.DTORqRs;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class BOSignOnRQ extends DTORqRs {

    @NotNull(message = "additionalData can't be null")
    @NotEmpty(message = "additionalData can't be empty")
    private String additionalData;

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }
}