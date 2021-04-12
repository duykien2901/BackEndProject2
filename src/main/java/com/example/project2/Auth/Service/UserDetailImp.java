package com.example.project2.Auth.Service;

import com.example.project2.Auth.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class UserDetailImp implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private GrantedAuthority authority;

    public static UserDetailImp build(UserEntity userEntity) {
        GrantedAuthority authorityUser;
        if(userEntity.getPermission() == 1) {
            authorityUser = new SimpleGrantedAuthority("ROLE_ADMIN");
        }else if(userEntity.getPermission() == 2) {
            authorityUser = new SimpleGrantedAuthority("ROLE_TEACHER");
        }else {
            authorityUser = new SimpleGrantedAuthority("ROLE_STUDENT");
        }
        return new UserDetailImp(userEntity.getId(),userEntity.getUsername(), userEntity.getPassword(), authorityUser);
    }

    public UserDetailImp(Integer id,String username, String password, GrantedAuthority authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public GrantedAuthority getAuthority() {
        return authority;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
