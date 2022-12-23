package org.yugo.backend.YuGo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.yugo.backend.YuGo.security.auth.RestAuthenticationEntryPoint;
import org.yugo.backend.YuGo.security.auth.TokenAuthenticationFilter;
import org.yugo.backend.YuGo.service.SecurityUserDetailsService;
import org.yugo.backend.YuGo.security.auth.TokenUtils;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig {
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private TokenUtils tokenUtils;

    public WebSecurityConfig(TokenUtils tokenUtils, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.tokenUtils = tokenUtils;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new SecurityUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService());
        authProvider.setPasswordEncoder(this.passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().authenticationEntryPoint(this.restAuthenticationEntryPoint);
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests().requestMatchers("/api/user/login").permitAll().
                requestMatchers(toH2Console()).permitAll().anyRequest().authenticated()
                .and().addFilterBefore(new TokenAuthenticationFilter(this.tokenUtils, this.userDetailsService()),
                        BasicAuthenticationFilter.class);
        http.authenticationProvider(this.authenticationProvider());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
//            web.ignoring().requestMatchers(HttpMethod.GET,
//                    "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
        };
    }
}
