package com.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.security.entity.DemoEntity;
import com.security.repository.DemoRepository;

@Component
public class DemoEntityUserDetailsService implements UserDetailsService {

	@Autowired
	private DemoRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<DemoEntity> demo = repo.findByNameOrEmail(username, username);

		return demo.map(DemoEntityUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("no user found with username : " + username));
	}

}
