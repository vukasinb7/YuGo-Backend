package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.PasswordResetCode;
import org.yugo.backend.YuGo.model.User;

public interface PasswordResetCodeService {
    PasswordResetCode get(Integer code);
    PasswordResetCode generateCode(User user);
    void setCodeInvalid(PasswordResetCode code);
}
