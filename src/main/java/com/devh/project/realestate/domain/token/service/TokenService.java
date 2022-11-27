package com.devh.project.realestate.domain.token.service;

import com.devh.project.realestate.domain.token.entity.Token;
import com.devh.project.realestate.domain.token.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token getToken() {
        log.info("select token");
        return this.tokenRepository.findById(1L).orElseThrow();
    }
}
