package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	private final UserRepository repository;
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		repository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User
				(username, currentUser.getPasswordHash(), AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}
	
}
