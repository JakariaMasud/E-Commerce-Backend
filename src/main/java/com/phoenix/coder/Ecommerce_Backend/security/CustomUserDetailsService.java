package com.phoenix.coder.Ecommerce_Backend.security;

import com.phoenix.coder.Ecommerce_Backend.models.UserModel;
import com.phoenix.coder.Ecommerce_Backend.services.UserModelService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    UserModelService userModelService;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userModelService.getUserByEmail(username);
        return User.withUsername(userModel.getEmail())
                .password(userModel.getPassword())
                .authorities(new SimpleGrantedAuthority(userModel.getRole())).build();
    }
}
