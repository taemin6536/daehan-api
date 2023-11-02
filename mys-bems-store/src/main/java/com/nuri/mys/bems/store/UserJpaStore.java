package com.nuri.mys.bems.store;

import com.nuri.mys.bems.store.jwt.repository.UserJpaRepository;
import com.nuri.mys.bems.domain.jwt.entity.User;
import com.nuri.mys.bems.domain.store.common.UserStore;
import com.nuri.mys.bems.store.jwt.jpo.UserJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserJpaStore implements UserStore {

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Override
	public List<User> findAll() {
		return userJpaRepository.findAll().stream().filter(Objects::nonNull).map(UserJpo::toDomain).collect(Collectors.toList());
	}

//	@Override
//	public List<User> findAllById(Iterable<String> ids) {
//		return userJpaRepository.findAllById(ids).stream().filter(Objects::nonNull).map(UserJpo::toDomain).collect(Collectors.toList());
//	}

	@Override
	public User findById(String id) {
		UserJpo userJpo = userJpaRepository.findById(id).orElse(null);
		if (userJpo != null) {
			return userJpo.toDomain();
		}
		return null;
	}

	@Override
	public boolean existsById(String id) {
		return userJpaRepository.existsById(id);
	}

	@Override
	public User save(User user) {
		return userJpaRepository.save(new UserJpo(user)).toDomain();
	}
	
	@Override
	public long count() {
		return userJpaRepository.count();
	}

	@Override
	public void deleteById(String id) {
		userJpaRepository.deleteById(id);
	}

	@Override
	public void delete(User user) {
		userJpaRepository.deleteById(user.getUserId());
	}

	@Override
	public User findByUserId(String userId) {
		UserJpo userJpo = userJpaRepository.findByUserId(userId).orElse(null);
		if (userJpo != null) {
			return userJpo.toDomain();
		}
		return null;
	}
}
