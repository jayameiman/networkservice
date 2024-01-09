package id.co.bni.qris.service.auth;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.bni.qris.constant.ERole;
import id.co.bni.qris.domain.dto.auth.AuthRequestDTO;
import id.co.bni.qris.domain.dto.auth.JwtResponseDTO;
import id.co.bni.qris.domain.model.user.UserInfo;
import id.co.bni.qris.domain.model.user.UserRoles;
import id.co.bni.qris.repository.RoleRepository;
import id.co.bni.qris.repository.UserRepository;

@Service
public class AuthService {
    public static Logger logger = LogManager.getLogger(AuthService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtService jwtUtils;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<Object> register(AuthRequestDTO registerDto){
        logger.info("register service");
        // if (Boolean.TRUE.equals(userRepository.existsByUsername(registerDto.getUsername()))) {
        //     return Response.build("username exist", null, null, HttpStatusCode.valueOf(400));
        // }

        UserInfo user = UserInfo.builder().username(registerDto.getUsername()).password(encoder.encode(registerDto.getPassword())).build();

        Set<UserRoles> roles = new HashSet<>();

        Optional<UserRoles> userRole = roleRepository.findByName(ERole.USER.name());
        if(userRole.isEmpty()){
            logger.info("Role user not found");
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        roles.add(userRole.get());

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }

    // public ResponseEntity<Object> register(UserRequest userRequest) {
    //     logger.info("register service");

    //     if(userRequest.getUsername() == null){
    //         throw new RuntimeException("Parameter username is not found in request..!!");
    //     } else if(userRequest.getPassword() == null){
    //         throw new RuntimeException("Parameter password is not found in request..!!");
    //     }

    //     String encodedPassword = encoder.encode(userRequest.getPassword());

    //     UserInfo user = modelMapper.map(userRequest, UserInfo.class);
    //     user.setPassword(encodedPassword);
 
    //     UserInfo savedUser = userRepository.save(user);
    //     userRepository.refresh(savedUser);
    //     UserResponse userResponse = UserResponse.builder().id(savedUser.getId()).username(savedUser.getUsername()).build();
        
    //     return new ResponseEntity<>(userResponse, HttpStatusCode.valueOf(200));
    // }

    public ResponseEntity<Object> login(AuthRequestDTO loginDto){
        logger.info("login service");
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        String jwt = "";
        if (authentication.isAuthenticated()) {
            jwt = jwtUtils.generateToken(loginDto.getUsername());
        }else{
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwt).build(), HttpStatusCode.valueOf(200));
    }
}
