package id.co.bni.qris.repositori;

import id.co.bni.qris.mpm.model.user.UserInfo;
import id.co.bni.qris.utils.RefreshableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RefreshableRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
    UserInfo findFirstById(Long id);

}