package com.devh.project.realestate.domain.token.repository;

import com.devh.project.realestate.domain.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
