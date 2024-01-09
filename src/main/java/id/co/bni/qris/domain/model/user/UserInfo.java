package id.co.bni.qris.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserInfo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "username", nullable = false)
        private String username;

        @JsonIgnore
        @Column(name = "password", nullable = false)
        private String password;

        @ManyToMany(fetch = FetchType.EAGER)
        private Set<UserRoles> roles = new HashSet<>();
}
