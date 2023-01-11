package org.yugo.backend.YuGo.annotation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.model.User;

@Aspect
@Component
public class AuthorizeSelfAspect {
    @Before("@annotation(AuthorizeSelf)")
    public boolean enabled() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        System.out.println(user.getEmail());
        return true;
    }

}