package com.example.hmsapi;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.hmsapi.services.UserService;
import com.example.hmsapi.models.Role;
import com.example.hmsapi.models.User;
import com.example.hmsapi.services.RoleService;

@SpringBootApplication
//@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class HmsApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HmsApiApplication.class, args);
		
//		ApplicationContext ctx = SpringApplication.run(HmsApiApplication.class, args);
//		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	CommandLineRunner run(UserService userService, RoleService roleService) {
//		return args->{
//			
//			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
//			roleService.saveRole(new Role(null, "ROLE_STAFF"));
//			roleService.saveRole(new Role(null, "ROLE_USER"));
//			
////			userService.saveUser(new User(null, "kmrchethan", "kmrchethan@gmail.com","password", new HashSet<>()));
////			
////			userService.addUserRole("kmrchethan", "ROLE_ADMIN");
//			
//		};
//	}

}
