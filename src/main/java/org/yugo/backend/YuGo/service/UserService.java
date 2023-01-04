package org.yugo.backend.YuGo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User insertUser(User user);
    User getUser(Integer id);
    User updateUser(User userUpdate);
    List<User> getAllUsers();
    UserActivation insertUserActivation(UserActivation userActivation);
    Optional<UserActivation> getUserActivation(Integer id);
    List<UserActivation> getAllUserActivations();
    Page<User> getUsersPage(Pageable page);
    void blockUser(Integer userId);
    void unblockUser(Integer userId);
    void activateUser(Integer activationId);
}
