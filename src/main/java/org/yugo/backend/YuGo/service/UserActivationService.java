package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.UserActivation;

import java.util.List;
import java.util.Optional;

public interface UserActivationService {
    UserActivation insertUserActivation(UserActivation userActivation);

    List<UserActivation> getAllUserActivations();

    Optional<UserActivation> getUserActivation(Integer id);

    void activateUser(Integer activationId);
}
