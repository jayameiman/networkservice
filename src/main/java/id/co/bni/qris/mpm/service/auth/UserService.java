package id.co.bni.qris.mpm.service.auth;

import id.co.bni.qris.mpm.dto.auth.UserRequest;
import id.co.bni.qris.mpm.dto.auth.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();
}
