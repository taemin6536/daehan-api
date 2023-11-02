package com.nuri.mys.bems.store.jwt.repository;

import com.nuri.mys.bems.store.jwt.jpo.TokenJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenJpaRepository extends JpaRepository<TokenJpo, String> {
	List<TokenJpo> findByAudience(String audience);
}
