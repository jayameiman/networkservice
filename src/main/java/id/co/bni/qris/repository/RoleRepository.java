package id.co.bni.qris.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.bni.qris.domain.model.user.UserRoles;

@Repository
public interface RoleRepository extends JpaRepository<UserRoles, Long>{
        Optional<UserRoles> findByName(String name);
}
