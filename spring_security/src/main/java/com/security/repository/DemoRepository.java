package com.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.entity.DemoEntity;

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {

	boolean existsByName(String name);

	Optional<DemoEntity> findByNameOrEmail(String name, String email);
}
