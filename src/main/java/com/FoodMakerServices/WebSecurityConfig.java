package com.FoodMakerServices;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.FoodMakerServices.entity.Usuario;
import com.FoodMakerServices.security.JWTAuthorizationFilter;
import com.FoodMakerServices.security.JWTAuthtenticationFilter;
import com.FoodMakerServices.security.UserDetailsServiceImplJwt;

import lombok.AllArgsConstructor;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailsServiceImplJwt userDetailsService;
	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    PasswordEncoder encoder() {
    	return new BCryptPasswordEncoder();
    };
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
    	JWTAuthtenticationFilter JWTAuthtenticationFilter = new JWTAuthtenticationFilter();
    	JWTAuthtenticationFilter.setAuthenticationManager(authManager);
    	JWTAuthtenticationFilter.setFilterProcessesUrl("/login");
    	
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                /*.authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/").permitAll()
                    .requestMatchers(HttpMethod.POST, "/registrar").permitAll()
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/anadirReceta").permitAll()
                    .anyRequest().authenticated()
                )*/
                .addFilter(JWTAuthtenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService((UserDetailsService) userDetailsService).passwordEncoder(encoder()).and().build();
    }
    
    @Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:5500").allowedMethods("*").allowedHeaders("*");
			}
		};
	}
}
