package org.yugo.backend.YuGo.security;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.yugo.backend.YuGo.model.RefreshToken;
import org.yugo.backend.YuGo.model.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token);

    @Transactional
    @Modifying
    int deleteByUser(User user);
}
