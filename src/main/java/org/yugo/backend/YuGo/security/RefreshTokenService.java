package org.yugo.backend.YuGo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.ForbiddenException;
import org.yugo.backend.YuGo.exceptions.NotFoundException;
import org.yugo.backend.YuGo.model.RefreshToken;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.UserRepository;
import org.yugo.backend.YuGo.service.UserService;

import java.sql.Ref;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    // Period vazenja refresh tokena - 1 dan
    @Value("86400000")
    private int REFRESH_TOKEN_EXPIRES_IN;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(UserService userService, RefreshTokenRepository refreshTokenRepository){
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken findByToken(String token) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByToken(token);
        if (refreshTokenOptional.isPresent()){
            return refreshTokenOptional.get();
        }
        throw new NotFoundException("Refresh token not found!");
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(LocalDateTime.now().plus(REFRESH_TOKEN_EXPIRES_IN, ChronoUnit.MILLIS));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new ForbiddenException("Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    public int deleteByUserId(Integer userId) {
        return refreshTokenRepository.deleteByUser(userService.getUser(userId));
    }
}
