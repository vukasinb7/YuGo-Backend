package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.exceptions.NotFoundException;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.PasswordResetCode;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.PasswordResetCodeRepository;

import java.time.Duration;
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
        PasswordResetCode code = new PasswordResetCode(user, Duration.ofDays(1));
        passwordResetCodeRepository.save(code);
        return code;
    }

    @Override
    public PasswordResetCode get(String code) {
        Optional<PasswordResetCode> passwordResetCodeOpt = passwordResetCodeRepository.findById(code);
        if (passwordResetCodeOpt.isPresent()){
            return passwordResetCodeOpt.get();
        }
        throw new BadRequestException("Code is expired or not correct!");
    }

    @Override
    public void delete(String code) {
        passwordResetCodeRepository.deleteById(code);
    }
}
