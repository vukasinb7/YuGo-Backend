package org.yugo.backend.YuGo.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.PasswordResetCode;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.model.UserActivation;

import java.io.IOException;
import java.time.Duration;

@Service
public class MailServiceImpl implements MailService {
    private final SendGrid sendGrid;
    private final PasswordResetCodeService passwordResetCodeService;
    private final UserActivationService userActivationService;

    @Autowired
    public MailServiceImpl(SendGrid sendGrid, PasswordResetCodeService passwordResetCodeService,
                           UserActivationService userActivationService) {
        this.sendGrid = sendGrid;
        this.passwordResetCodeService = passwordResetCodeService;
        this.userActivationService = userActivationService;
    }

    @Override
    public void sendPasswordResetMail(User user) {
        Email from = new Email("yugohailing@outlook.com");

        PasswordResetCode code = passwordResetCodeService.generateCode(user);

        Email to = new Email(user.getEmail());
        Mail mail = new Mail();
        mail.setFrom(from);
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.addDynamicTemplateData("url_page","http://localhost:4200/reset/"+code.getCode()+"/"+user.getId());
        personalization.addDynamicTemplateData("subject","Reset Password");
        mail.addPersonalization(personalization);
        mail.setTemplateId("d-d3e6dfa729f24a98b2fce6aa128ee520");

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

    @Override
    public void sendActivationMail(User user) {
        Email from = new Email("yugohailing@outlook.com");

        UserActivation activation = new UserActivation(user, Duration.ofDays(1));
        userActivationService.insertUserActivation(activation);

        Email to = new Email(user.getEmail());
        Mail mail = new Mail();
        mail.setFrom(from);
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.addDynamicTemplateData("url_page","http://localhost:4200/reset/"+ activation.getCode() +"/"+user.getId());
        personalization.addDynamicTemplateData("subject","Reset Password");
        mail.addPersonalization(personalization);
        mail.setTemplateId("d-d3e6dfa729f24a98b2fce6aa128ee520");

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
