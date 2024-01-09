package id.co.bni.qris.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

import id.co.bni.qris.domain.model.user.UserRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    private Long id;

    @NotBlank(message = "username can't be empty")
    @NotNull(message = "username can't be null")
    private String username;

    @NotBlank(message = "password can't be empty")
    @NotNull(message = "password can't be null")
    private String password;
    private Set<UserRoles> roles;
}
