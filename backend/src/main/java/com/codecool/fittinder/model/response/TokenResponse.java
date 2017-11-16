package com.codecool.fittinder.model.response;

import lombok.Getter;

@Getter
public class TokenResponse {

    String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
