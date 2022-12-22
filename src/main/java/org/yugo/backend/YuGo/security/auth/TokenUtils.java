package org.yugo.backend.YuGo.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.yugo.backend.YuGo.model.User;

@Component
public class TokenUtils {
    @Value("YuGo")
    private String APP_NAME;
    @Value("some-secret")
    public String SECRET;
    @Value("1800000")
    private int EXPIRES_IN;
    @Value("Authorization")
    private String AUTH_HEADER;
    private static final String AUDIENCE_WEB = "web";
    private SignatureAlgorithm SIGNATURE_ALGORITHM;

    public TokenUtils() {
        this.SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    }

    public String generateToken(String username) {
        return Jwts.builder().setIssuer(this.APP_NAME).setSubject(username).setAudience(this.generateAudience()).setIssuedAt(new Date()).setExpiration(this.generateExpirationDate()).signWith(this.SIGNATURE_ALGORITHM, this.SECRET).compact();
    }

    private String generateAudience() {
        return "web";
    }

    private Date generateExpirationDate() {
        return new Date((new Date()).getTime() + (long)this.EXPIRES_IN);
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = this.getAuthHeaderFromHeader(request);
        return authHeader != null && authHeader.startsWith("Bearer ") ? authHeader.substring(7) : null;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (ExpiredJwtException var4) {
            throw var4;
        } catch (Exception var5) {
            username = null;
        }

        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (ExpiredJwtException var4) {
            throw var4;
        } catch (Exception var5) {
            issueAt = null;
        }

        return issueAt;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (ExpiredJwtException var4) {
            throw var4;
        } catch (Exception var5) {
            audience = null;
        }

        return audience;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (ExpiredJwtException var4) {
            throw var4;
        } catch (Exception var5) {
            expiration = null;
        }

        return expiration;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = (Claims)Jwts.parser().setSigningKey(this.SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException var4) {
            throw var4;
        } catch (Exception var5) {
            claims = null;
        }

        return claims;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User)userDetails;
        String username = this.getUsernameFromToken(token);
        Date created = this.getIssuedAtDateFromToken(token);
        return username != null && username.equals(userDetails.getUsername()) ;
        //&& !this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return lastPasswordReset != null && created.before(lastPasswordReset);
    }

    public int getExpiredIn() {
        return this.EXPIRES_IN;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(this.AUTH_HEADER);
    }
}
