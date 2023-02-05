package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.User;

public interface MailService {
    void sendPasswordResetMail(User user);
    void sendActivationMail(User user);
}
