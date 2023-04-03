package com.rest_jpa.security;

import com.rest_jpa.entity.RefreshToken;
import com.rest_jpa.entity.User;
import com.rest_jpa.exceptions.TokenRefreshException;
import com.rest_jpa.repository.RefreshTokenRepository;
import com.rest_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.rest_jpa.exceptions.ErrorKey.REFRESH_TOKEN_EXPIRED;
import static com.rest_jpa.exceptions.ErrorKey.REFRESH_TOKEN_NOT_FOUND;

@Service
public class RefreshTokenService {
    @Value("${jwt.refresh.token.expired}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken findByToken(String token) {
        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByToken(token);
        return refreshTokenOpt.orElseThrow(() -> new TokenRefreshException(REFRESH_TOKEN_NOT_FOUND, token));
    }

    @Transactional
    public RefreshToken createRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(REFRESH_TOKEN_EXPIRED, token.getToken());
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        return refreshTokenRepository.deleteByUser(userOpt.get());
    }
}
