package com.nuri.mys.bems.domain.store.common;


import com.nuri.mys.bems.domain.jwt.entity.Token;

import java.util.List;

public interface TokenStore {

	List<Token> findAll();

//	List<Token> findAllById(Iterable<String> ids);

	Token save(Token tokenJpo);

	Token findById(String id);

	boolean existsById(String id);

	long count();

	void deleteById(String id);

	void delete(Token tokenJpo);

	List<Token> findByAudience(String audience);

}