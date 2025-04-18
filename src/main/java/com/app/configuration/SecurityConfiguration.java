package com.app.configuration;

import org.springframework.security.config.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(requests -> requests
				.antMatchers("/users/saveUser")
				.permitAll()
				.anyRequest()
				.authenticated())
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(management -> management
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return httpSecurity.build();
	}

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}

}
