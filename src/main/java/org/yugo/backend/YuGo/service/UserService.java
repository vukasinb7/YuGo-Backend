package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User insertUser(User user);

    List<User> getAllUsers();

    Optional<User> getUser(Integer id);

    UserActivation insertUserActivation(UserActivation userActivation);

    List<UserActivation> getAllUserActivations();

    Optional<UserActivation> getUserActivation(Integer id);

    public Page<User> getUsersPage(Pageable page);

    public void authenticateUser(String email, String password);

    public boolean blockUser(Integer userId);

    public boolean unblockUser(Integer userId);
}
