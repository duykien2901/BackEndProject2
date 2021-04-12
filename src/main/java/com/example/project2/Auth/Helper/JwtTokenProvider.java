package com.example.project2.Auth.Helper;

import com.example.project2.Auth.Service.UserDetailImp;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    // String secret
    private final String JWT_Secret = "qldt";
    // expiration jwt
    private final long JWT_EXPIRATION = 604800000L;

    public String generateToken(UserDetailImp userDetailImp) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // create jwt String from username
        return Jwts.builder()
                .setSubject(userDetailImp.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, JWT_Secret)
                .compact();
    }

    public String getUsernameFromJwt(String jwtToken) {
        return Jwts.parser().setSigningKey(JWT_Secret).parseClaimsJws(jwtToken).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_Secret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            log.error("Invalid JWT signature: {}", e.getMessage());
        }catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}