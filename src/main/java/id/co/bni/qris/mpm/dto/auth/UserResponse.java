package id.co.bni.qris.mpm.dto.auth;

import id.co.bni.qris.mpm.model.user.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String username;
    private Set<UserRoles> roles;
}
