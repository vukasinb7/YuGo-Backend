package org.yugo.backend.YuGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yugo.backend.YuGo.model.UserActivation;

public interface UserActivationRepository extends JpaRepository<UserActivation,Integer> {
}
