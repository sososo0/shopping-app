package com.server.myselectshop.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRoleEnum {
    ADMIN(Authority.ADMIN),
    USER(Authority.USER);

    private final String authority;

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
