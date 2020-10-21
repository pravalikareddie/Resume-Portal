package com.pravalika.projects.resumeportal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pravalika.projects.resumeportal.models.CustomUserDetails;
import com.pravalika.projects.resumeportal.models.User;
import com.pravalika.projects.resumeportal.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(()->new UsernameNotFoundException("No such user"));
		return user.map(CustomUserDetails::new).get();
	}

}
