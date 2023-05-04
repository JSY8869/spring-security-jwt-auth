package io.github.cokelee777.springsecurityjwtauth.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfiguration {

    private static final String[] PUBLIC_END_POINT = {"/", "/sign-in", "/sign-up", "/error"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
         * REST API 서버를 기반으로 하기 때문에 CSRF 필터 해제, 폼 로그인 사용 X
         * JWT 토큰 기반의 인증, 인가를 위해 HTTP Basic 인증 사용 X, 세션 생성 및 사용 X
         */
        http.csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 인가 API
        http.authorizeHttpRequests()
            .requestMatchers(PUBLIC_END_POINT).permitAll()
            .anyRequest().authenticated();

        return http.build();
    }
}
