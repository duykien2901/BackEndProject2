package com.example.project2.Auth.Model;

import com.example.project2.Auth.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class LoginResponse {
    String jwt;
}
