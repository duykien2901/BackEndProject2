package com.example.project2.Auth.Service;

import com.example.project2.Auth.Entity.UserEntity;
import com.example.project2.Auth.Model.LoginRequest;
import com.example.project2.Auth.Model.SignUpRequest;
import com.example.project2.Auth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found!"));
            return UserDetailImp.build(userEntity);

    }

    public Optional<UserEntity> save(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userEntity.setPermission(signUpRequest.getPermission());
        userRepository.save(userEntity);
        Optional<UserEntity> user= userRepository.findByUsername(signUpRequest.getUsername());
        return user;
    }
}
