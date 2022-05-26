package com.example.hmsapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.hmsapi.configurations.auth.Authenticate;
import com.example.hmsapi.configurations.auth.Authorization;

@Configuration 
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder passwordEncoder;
	
	public WebSecurityConfigurations(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		Authenticate authenticate = new Authenticate(authenticationManagerBean());
		authenticate.setFilterProcessesUrl("/api/v1/login");
		http
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.authorizeRequests()
//        .antMatchers("/api/login", "/api/v1/refresh/token").permitAll();
		http.authorizeRequests()
		.antMatchers("/api/v1/signin", "/api/v1/signup", "/actuator/**").permitAll()
        .antMatchers("/api/v1/**").authenticated();
		
		http.authorizeRequests().anyRequest().authenticated();
		
//		http.authorizeRequests()
//		.antMatchers("/api/v1/**").hasAnyAuthority("ROLE_ADMIN");
		
//		http.authorizeRequests().anyRequest().authenticated();
//		http.authorizeRequests().anyRequest().permitAll();
		
		http.addFilter(authenticate);
		http.addFilterBefore(new Authorization(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		 return super.authenticationManagerBean();
	}
	
}
