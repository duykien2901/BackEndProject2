package com.example.project2.Auth.Service;

import com.example.project2.Auth.Entity.UserEntity;
import com.example.project2.Auth.Model.LoginRequest;
import com.example.project2.Auth.Model.SignUpRequest;
import com.example.project2.Auth.Repository.UserRepository;
import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.Commom.Exception.InternalServerError;
import com.example.project2.Commom.Exception.UsernameIsExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity createEntity(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userEntity.setPermission(signUpRequest.getPermission());
        userEntity.setStatus(1);
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found!"));
        return UserDetailImp.build(userEntity);
    }

    public Map save(SignUpRequest signUpRequest) throws UsernameIsExist {
        UserEntity userEntity = new UserEntity();
        Map<String, String> result = new HashMap<>();
        if (!userRepository.existsByUsername(signUpRequest.getUsername())) {
           userEntity = createEntity(signUpRequest);
            userRepository.save(userEntity);
            result.put("message", "success");
            return result;
        }
        throw new UsernameIsExist("Username is exist");
    }

    public Map changeAccount(Integer id, SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        Map<String, String> result = new HashMap<>();
        if (!userRepository.existsByUsername(signUpRequest.getUsername())) {
            userEntity = createEntity(signUpRequest);
            userEntity.setId(id);
            userRepository.save(userEntity);
            result.put("message", "success");
            return result;
        }
        throw new UsernameIsExist("Username is exist");
    }

    public List<UserEntity> getUserAccount(Pageable pageable) {
        return userRepository.findByPermissionNotAndAndStatusNotOrderByPermissionAsc(1, 0,  pageable).toList();
    }

    public Map deleteById(Integer id) throws IdNotFoundException, InternalServerError{
        Map<String, String> result = new HashMap<>();
        try {
            if (userRepository.findById(id).isPresent()) {
                UserEntity userEntity = userRepository.findById(id).get();
                userEntity.setStatus(0);
                userRepository.save(userEntity);
                result.put("message", "success");
                return result;
            }
            throw new IdNotFoundException(id);
        } catch (Exception ex) {
            logger.error("error", ex.getMessage());
            throw new InternalServerError("Can't delete");
        }
    }
}
