package id.co.bni.qris.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

import id.co.bni.qris.domain.model.user.UserRoles;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private Set<UserRoles> roles;
}
