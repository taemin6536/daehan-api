package com.nuri.mys.bems.store.jwt.repository;


import com.nuri.mys.bems.store.jwt.jpo.UserJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpo, String> {
	Optional<UserJpo> findByUserId(String userId);
}
