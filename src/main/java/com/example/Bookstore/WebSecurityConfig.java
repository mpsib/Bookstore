package com.example.Bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/delete/**").hasRole("ADMIN")
				.and()
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.defaultSuccessUrl("/booklist", true)
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user1 = User
        		.withUsername("user")
        		.password(passwordEncoder.encode("user"))
        		.roles("USER")
        		.build();
		
		UserDetails admin1 = User
        		.withUsername("admin")
        		.password(passwordEncoder.encode("admin"))
        		.roles("USER", "ADMIN")
        		.build();
		List<UserDetails> users = new ArrayList<>();
		users.add(user1);
		users.add(admin1);
		return new InMemoryUserDetailsManager(users);
	}
}
