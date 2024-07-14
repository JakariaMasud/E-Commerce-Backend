package com.phoenix.coder.Ecommerce_Backend.Utils;

import com.phoenix.coder.Ecommerce_Backend.constants.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtUtils {
    private Environment environment;

    public void validateTokenAndSetContext(String jwt) {
        String secret = environment.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        if (null != secretKey) {
            Claims claims = Jwts.parser().verifyWith(secretKey)
                    .build().parseSignedClaims(jwt).getPayload();
            String username = String.valueOf(claims.get("username"));
            String authorities = String.valueOf(claims.get("authorities"));
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public String generateJwtToken(Authentication authentication) {
        String secret = environment.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder().issuer("Phoenix-Coder").subject("JWT Token")
                .claim("username", authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(secretKey).compact();

        return jwt;
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(ApplicationConstants.JWT_HEADER);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove Bearer prefix
        }
        return null;
    }
}
