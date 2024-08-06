package com.nuri.mys.bems.service.jwt.security;

import com.nuri.mys.bems.domain.jwt.entity.User;
import com.nuri.mys.bems.domain.store.common.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserStore userStore;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userStore.findById(username);
		if (user == null) {
			throw new UsernameNotFoundException("Fail to find user");
		}
		return user;
	}
}