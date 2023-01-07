package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.User;

public interface SendGridMailService {
    void sendMail(User user);
}
