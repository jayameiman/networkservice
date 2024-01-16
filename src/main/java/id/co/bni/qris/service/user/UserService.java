package id.co.bni.qris.service.user;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> getUser();
    ResponseEntity<Object> getAllUser();
}
