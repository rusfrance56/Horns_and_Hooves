package com.rest_jpa.entity.to;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
