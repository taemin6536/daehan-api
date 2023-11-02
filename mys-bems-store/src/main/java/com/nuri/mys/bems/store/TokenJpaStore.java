package com.nuri.mys.bems.store;

import com.nuri.mys.bems.store.jwt.repository.TokenJpaRepository;
import com.nuri.mys.bems.domain.jwt.entity.Token;
import com.nuri.mys.bems.domain.store.common.TokenStore;
import com.nuri.mys.bems.store.jwt.jpo.TokenJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TokenJpaStore implements TokenStore {

	@Autowired
	private TokenJpaRepository tokenJpaRepository;

	@Override
	public List<Token> findAll() {
		return tokenJpaRepository.findAll().stream().filter(Objects::nonNull).map(TokenJpo::toDomain).collect(Collectors.toList());
	}

//	@Override
//	public List<Token> findAllById(Iterable<String> ids) {
//		return tokenJpaRepository.findAllById(ids).stream().filter(Objects::nonNull).map(TokenJpo::toDomain).collect(Collectors.toList());
//	}

	@Override
	public Token save(Token token) {
		return tokenJpaRepository.save(new TokenJpo(token)).toDomain();
	}

	@Override
	public Token findById(String id) {
		TokenJpo tokenJpo = tokenJpaRepository.findById(id).orElse(null);
		if (tokenJpo != null) {
			return tokenJpo.toDomain();
		}
		return null;
	}

	@Override
	public boolean existsById(String id) {
		return tokenJpaRepository.existsById(id);
	}

	@Override
	public long count() {
		return tokenJpaRepository.count();
	}

	@Override
	public void deleteById(String id) {
		tokenJpaRepository.deleteById(id);
	}

	@Override
	public void delete(Token token) {
		tokenJpaRepository.deleteById(token.getTokenId());
	}

	@Override
	public List<Token> findByAudience(String audience) {
		return tokenJpaRepository.findByAudience(audience).stream().filter(Objects::nonNull).map(TokenJpo::toDomain).collect(Collectors.toList());
	}
}
