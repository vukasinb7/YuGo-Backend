package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUser(Integer id);

    UserActivation saveUserActivation(UserActivation userActivation);

    List<UserActivation> getAllUserActivations();

    Optional<UserActivation> getUserActivation(Integer id);
}
