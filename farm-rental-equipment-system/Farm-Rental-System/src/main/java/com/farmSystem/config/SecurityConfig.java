package com.farmSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.farmSystem.filters.JwtAuthFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.POST, "/user").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/delivery/*/location").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/delivery/*/location").permitAll()
				.requestMatchers("/track").permitAll()

				// ✅ Allow all Swagger-related paths
				.requestMatchers(
					"/swagger-ui/**",           // CSS/JS/UI
					"/v3/api-docs/**",          // OpenAPI JSON
					"/swagger-ui.html",         // Redirect to index.html
					"/documentation/swagger-config"  // Fix for your specific 403
				).permitAll()

				// ✅ Static & custom resources
				.requestMatchers("/css/**", "/js/**", "/html/**", "/images/**").permitAll()
				.requestMatchers(
					"/swagger.html", "/documentation", "/Track.html", "/GetMap.html",
					"/LiveLocation.html", "/authenticate", "/api/payment/payment-callback",
					"/index.html", "/success.html", "/failure.html","/api/payment/create-order"
				).permitAll()

				.anyRequest().authenticated()
			);
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new CustomUserDetailsService();
//	}
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		
		return new ProviderManager(daoAuthenticationProvider);
		
	}
}
