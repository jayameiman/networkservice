package id.co.bni.qris.repository;

import id.co.bni.qris.domain.model.user.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
    UserInfo findFirstById(Long id);

}