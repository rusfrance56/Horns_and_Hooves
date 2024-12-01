package com.rest_jpa.entity.to;

import lombok.Data;

@Data
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;

    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
