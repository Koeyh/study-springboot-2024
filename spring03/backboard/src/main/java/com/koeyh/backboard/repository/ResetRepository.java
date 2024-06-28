package com.koeyh.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koeyh.backboard.entity.Reset;
import java.util.Optional;


@Repository
public interface ResetRepository extends JpaRepository<Reset, Integer>{
    
    Optional<Reset> findByUuid(String uuid);
}
