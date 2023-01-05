package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.PasswordResetCode;
import org.yugo.backend.YuGo.model.User;

public interface PasswordResetCodeService {
    PasswordResetCode generateCode(User user);
    PasswordResetCode get(String code);
    void delete(String code);
}
