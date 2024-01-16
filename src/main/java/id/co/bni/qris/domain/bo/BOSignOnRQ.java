package id.co.bni.qris.domain.bo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class BOSignOnRQ extends BaseNetworkBody {

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