package com.devh.project.realestate.domain.token;

import com.devh.project.realestate.domain.token.entity.Token;
import com.devh.project.realestate.domain.token.repository.TokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenRepositoryTests {
    @Autowired
    private TokenRepository tokenRepository;

    @Test
    void save() {
        Token token = Token.create("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2Njk1MjI2ODgsImV4cCI6MTY2OTUzMzQ4OH0.QwuD-gqoZXd0ApJL_MN2NK2AQx2tcmt2xST7MIuRmvU");
        tokenRepository.save(token);

    }
}
