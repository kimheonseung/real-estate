package com.devh.project.realestate.domain.token.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @Id
    private long id;
    @Column
    private String token;

    private Token(long id, String token) {
        this.id = id;
        this.token = token;
    }

    public static Token create(String token) {
        return new Token(1, token);
    }

    public String getToken() {
        return "Bearer "+this.token;
    }
}
