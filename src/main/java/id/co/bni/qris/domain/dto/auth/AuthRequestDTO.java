package id.co.bni.qris.domain.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO {
    @NotBlank(message = "username can't be empty")
    @NotNull(message = "username can't be null")
    private String username;

    @NotBlank(message = "password can't be empty")
    @NotNull(message = "password can't be null")
    private String password;
}