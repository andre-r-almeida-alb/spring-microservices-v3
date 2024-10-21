package com.in28minutes.rest.webservices.restfulwebservices.security;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	@Order(1)
	@Profile("no-auth-enabled")
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
				.csrf(AbstractHttpConfigurer::disable)
				.headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin));
		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		1) All requests should be authenticated
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
//		2) If a request is not authenticated, use http basic
		// http.httpBasic(); // Deprecated in SB 3.1.x
		http.httpBasic(withDefaults()); // Starting from Spring Boot 3.1.x

//		3) CSRF -> POST, PUT
		// http.csrf().disable(); // Deprecated in SB 3.1.x
		http.csrf(AbstractHttpConfigurer::disable); // // Starting from Spring Boot 3.1.x


		return http.build();
	}



}
