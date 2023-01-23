package org.yugo.backend.YuGo.annotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.User;

@Aspect
@Component
public class AuthorizeSelfAspect {
    @Before("@annotation(authorizeSelf)")
    public void authorize(JoinPoint joinPoint, AuthorizeSelf authorizeSelf) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Object[] args = joinPoint.getArgs();
        ExpressionParser elParser = new SpelExpressionParser();
        Expression expression = elParser.parseExpression(authorizeSelf.pathToUserId());
        Integer userId = (Integer) expression.getValue(args);

        if (!user.getId().equals(userId)){
            throw new NotFoundException(authorizeSelf.message());
        }
    }
}