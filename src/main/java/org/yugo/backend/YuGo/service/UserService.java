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
    User getUserByEmail(String email);
    User updateUser(User userUpdate);
    List<User> getAllUsers();
    Page<User> getUsersPage(Pageable page);
    void blockUser(Integer userId);
    void unblockUser(Integer userId);
    void changePassword(Integer userId, String oldPassword, String newPassword);
    void sendPasswordResetCode(Integer userId);
    void resetPassword(Integer userId, String newPassword, String code);
}
