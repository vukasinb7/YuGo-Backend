package org.yugo.backend.YuGo.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.PasswordResetCode;
import org.yugo.backend.YuGo.model.User;

import java.io.IOException;

@Service
public class SendGridMailServiceImpl implements SendGridMailService {
    private final SendGrid sendGrid;
    private final PasswordResetCodeService passwordResetCodeService;

    @Autowired
    public SendGridMailServiceImpl(SendGrid sendGrid, PasswordResetCodeService passwordResetCodeService) {
        this.sendGrid = sendGrid;
        this.passwordResetCodeService = passwordResetCodeService;
    }

    @Override
    public void sendMail(User user) {
        Email from = new Email("yugohailing@outlook.com");

        PasswordResetCode code = passwordResetCodeService.generateCode(user);

        String subject = "Password reset request";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain", code.getCode());
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
