package com.nuri.mys.bems.domain.store.common;


import com.nuri.mys.bems.domain.jwt.entity.User;

import java.util.List;

public interface UserStore {

	List<User> findAll();

//	List<User> findAllById(Iterable<String> ids);

	User findById(String id);

	boolean existsById(String id);

	long count();

	void deleteById(String id);

	void delete(User entity);

	User findByUserId(String userId);

	User save(User user);

}