package com.codecool.fittinder.security;

class SecurityConstants {

    static final String SECRET = "SecretKeyToGenJWTs";
    static final Long EXPIRATION_TIME = 864_000_000L; // 10 days
    static final String HEADER_STRING = "Authorization";
    static final String TOKEN_PREFIX = "Bearer ";
}
