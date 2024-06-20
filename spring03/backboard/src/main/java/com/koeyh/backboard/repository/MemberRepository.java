package com.koeyh.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koeyh.backboard.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
