package com.spayker.account.auth.service.security;

import com.spayker.account.auth.domain.User;
import com.spayker.account.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *  Service layer for work user related data.
 **/
@Service
public class B2GUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	/**
	 *  Returns UserDetails object by its username.
	 *  @param username - String value to perform search
	 *  @return found UserDetails instance
	 *  @throws UsernameNotFoundException if user details were not found
	 **/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
}
