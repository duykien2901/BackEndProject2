package com.example.project2.Auth.Controller;

import com.example.project2.Auth.Entity.UserEntity;
import com.example.project2.Auth.Helper.JwtTokenProvider;
import com.example.project2.Auth.Model.LoginRequest;
import com.example.project2.Auth.Model.LoginResponse;
import com.example.project2.Auth.Model.SignUpRequest;
import com.example.project2.Auth.Repository.UserRepository;
import com.example.project2.Auth.Service.UserDetailImp;
import com.example.project2.Auth.Service.UserDetailsServiceImp;
import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.Commom.Exception.PermissRoleError;
import com.example.project2.Commom.Exception.UsernameOrPasswordNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(value = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImp userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // authen username and password

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // Set user to context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // return jwt
            String jwt = jwtTokenProvider.generateToken((UserDetailImp) authentication.getPrincipal());
            Optional<UserEntity> userEntity = userRepository.findByUsername(loginRequest.getUsername());
            return ResponseEntity.ok(new LoginResponse(jwt));
        } catch (Exception e) {
            throw new UsernameOrPasswordNotFound("Username or Password is not incorrect");
        }

    }

    @PostMapping("/api/signup")
    public ResponseEntity<Optional<UserEntity>> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(userService.save(signUpRequest));
    }

    @GetMapping("/api/admin")
    public ResponseEntity<?> test() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", "hello");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/api/account")
    public ResponseEntity<?> getAll() {
       List<UserEntity> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }
}
