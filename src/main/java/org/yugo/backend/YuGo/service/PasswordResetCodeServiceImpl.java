package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.model.PasswordResetCode;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.PasswordResetCodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordResetCodeServiceImpl implements PasswordResetCodeService {

    private final PasswordResetCodeRepository passwordResetCodeRepository;

    @Autowired
    public PasswordResetCodeServiceImpl(PasswordResetCodeRepository passwordResetCodeRepository){
        this.passwordResetCodeRepository = passwordResetCodeRepository;
    }

    @Override
    public PasswordResetCode generateCode(User user){
        passwordResetCodeRepository.setUserCodesInvalid(user.getId());
        PasswordResetCode code = new PasswordResetCode(user, Duration.ofDays(1));
        passwordResetCodeRepository.save(code);
        return code;
    }

    @Override
    public PasswordResetCode getValidCode(Integer userId) {
        Optional<PasswordResetCode> passwordResetCodeOpt = passwordResetCodeRepository.findByUserIdAndValidTrue(userId);
        if (passwordResetCodeOpt.isPresent()){
            PasswordResetCode passwordResetCode = passwordResetCodeOpt.get();
            if (!passwordResetCode.getDateCreated().plus(passwordResetCode.getLifeSpan()).isBefore(LocalDateTime.now())){
                return passwordResetCode;
            }
            setCodeInvalid(passwordResetCode);
        }
        throw new BadRequestException("Code is expired or not correct!");
    }

    @Override
    public void setCodeInvalid(PasswordResetCode code) {
        code.setValid(false);
        passwordResetCodeRepository.save(code);
    }

    @Override
    public User getUserByCode(String code){
        Optional<PasswordResetCode> passwordResetCodeOpt = passwordResetCodeRepository.findByCodeAndValidTrue(code);
        if (passwordResetCodeOpt.isPresent()){
            PasswordResetCode passwordResetCode = passwordResetCodeOpt.get();
            if (!passwordResetCode.getDateCreated().plus(passwordResetCode.getLifeSpan()).isBefore(LocalDateTime.now())){
                return passwordResetCode.getUser();
            }
            setCodeInvalid(passwordResetCode);
        }
        throw new BadRequestException("Code is expired or not correct!");
    }
}
