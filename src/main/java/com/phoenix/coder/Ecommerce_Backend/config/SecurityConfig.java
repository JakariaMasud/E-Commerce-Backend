package com.phoenix.coder.Ecommerce_Backend.config;

import com.phoenix.coder.Ecommerce_Backend.filters.JwtTokenGeneratorFilter;
import com.phoenix.coder.Ecommerce_Backend.filters.JwtTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,JwtTokenValidatorFilter jwtTokenValidatorFilter) throws Exception {
        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("/auth/login", "auth/signup").permitAll();
            authorize.anyRequest().authenticated();
        });
        http.csrf((csrf)->{
            csrf.disable();
        });
        http.cors((cors)->{
            cors.disable();
        });
        http.addFilterBefore(jwtTokenValidatorFilter, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterAfter(new JwtTokenGeneratorFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("Jakaria")
                .password(passwordEncoder().encode("26111994"))
                .authorities(new SimpleGrantedAuthority("ADMIN"))
                .build();
        UserDetails user = User.withUsername("Afrin")
                .password(passwordEncoder().encode("afrin2611"))
                .authorities(new SimpleGrantedAuthority("USER"))
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsManager userDetailsManager,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsManager);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider){
        return new ProviderManager(authenticationProvider);
    }

}