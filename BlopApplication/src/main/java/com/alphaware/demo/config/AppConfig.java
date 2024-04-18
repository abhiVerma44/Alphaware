package com.alphaware.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http
		.cors().and() // Enable CORS
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST, "/api/user/register").permitAll()
		.requestMatchers(HttpMethod.GET, "/api/user/signIn").permitAll()
		.requestMatchers(HttpMethod.GET, "/api/user/{email}").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET, "/api/posts/{id}").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET, "/api/posts/feed").hasRole("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/api/posts/{id}").hasRole("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/api/posts/{id}").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.POST, "/api/posts").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.POST, "/api/category**").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.GET, "/api/category").hasRole("ADMIN")
		.requestMatchers(HttpMethod.PUT, "/api/category/{id}").hasRole("ADMIN")
		.requestMatchers(HttpMethod.GET, "/api/category/{id}").hasRole("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/api/category/{id}").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.POST, "/api/comments/post/{postId}").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.PUT, "/api/comments/{id}").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.PUT, "/api/comments//post/{postId}").hasAnyRole("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/api/comments/{id}").hasAnyRole("ADMIN","USER")
		.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
		.anyRequest().hasRole("ADMIN")
		.and()
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin()
		.and()
		.httpBasic();

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	

}