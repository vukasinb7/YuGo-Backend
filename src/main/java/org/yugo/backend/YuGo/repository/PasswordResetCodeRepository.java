package org.yugo.backend.YuGo.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.PasswordResetCode;

import java.util.Optional;

public interface PasswordResetCodeRepository  extends JpaRepository<PasswordResetCode, Integer> {
    @Transactional
    @Modifying
    @Query("update PasswordResetCode c set c.valid = false where c.user.id = :userId")
    void setUserCodesInvalid(@Param("userId") Integer userId);
    Optional<PasswordResetCode> findByUserIdAndValidTrue(Integer userId);

}
