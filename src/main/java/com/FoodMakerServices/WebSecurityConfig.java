package com.FoodMakerServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.FoodMakerServices.entity.Usuario;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder encoder() {
    	return new BCryptPasswordEncoder();
    };
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(encoder());
    	
    	return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                                .requestMatchers("/").permitAll()
                                .requestMatchers(HttpMethod.POST, "/registrar").permitAll()
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    Usuario usuario() {
        return new Usuario();
    }

}
