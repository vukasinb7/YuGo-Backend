package org.yugo.backend.YuGo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.yugo.backend.YuGo.security.RestAccessDeniedHandler;
import org.yugo.backend.YuGo.security.RestAuthenticationEntryPoint;
import org.yugo.backend.YuGo.security.TokenAuthenticationFilter;
import org.yugo.backend.YuGo.service.SecurityUserDetailsService;
import org.yugo.backend.YuGo.security.TokenUtils;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig {
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final TokenUtils tokenUtils;

    public WebSecurityConfig(TokenUtils tokenUtils, RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                             RestAccessDeniedHandler restAccessDeniedHandler) {
        this.tokenUtils = tokenUtils;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
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
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests().
                requestMatchers(HttpMethod.POST, "/api/user/login").permitAll().
                requestMatchers(HttpMethod.POST, "/api/user/refreshToken").permitAll().
                requestMatchers(HttpMethod.GET,"api/user/logout").permitAll().
                requestMatchers(HttpMethod.GET, "/api/user/{email}/email").permitAll().
                requestMatchers(HttpMethod.POST, "/api/passenger").permitAll().
                requestMatchers(HttpMethod.GET, "/api/passenger/activate/{activationId}").permitAll().
                requestMatchers(HttpMethod.GET,"/api/user/{id}/resetPassword").permitAll().
                requestMatchers(HttpMethod.PUT,"/api/user/{id}/resetPassword").permitAll().
                requestMatchers(HttpMethod.POST,"/api/user/{email}/resetPassword").permitAll().
                requestMatchers(HttpMethod.PUT,"/api/user/resetPassword").permitAll().
                requestMatchers(HttpMethod.POST,"/api/unregisteredUser").permitAll().
                requestMatchers(HttpMethod.GET,"/api/vehicle/vehicles").permitAll().
                requestMatchers(HttpMethod.GET,"/api/ride/driver/{id}/active").permitAll().
                requestMatchers("/api/image/**").permitAll().
                requestMatchers("/api/vehicleType").permitAll().
                requestMatchers("/api/socket/**").permitAll().
                requestMatchers(toH2Console()).permitAll().
                anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedHandler(this.restAccessDeniedHandler).and()
                .exceptionHandling().authenticationEntryPoint(this.restAuthenticationEntryPoint).and()
                .addFilterBefore(new TokenAuthenticationFilter(this.tokenUtils, this.userDetailsService()),
                        BasicAuthenticationFilter.class)
                .authenticationProvider(this.authenticationProvider());
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
