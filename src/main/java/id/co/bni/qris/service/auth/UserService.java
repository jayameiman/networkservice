package id.co.bni.qris.service.auth;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> getUser();
    ResponseEntity<Object> getAllUser();
}
