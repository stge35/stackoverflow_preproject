package com.preproject.server.global.config;

import com.preproject.server.global.auth.filter.JwtAuthenticationFilter;
import com.preproject.server.global.auth.filter.JwtVerificationFilter;
import com.preproject.server.global.auth.handler.MemberAccessDeniedHandler;
import com.preproject.server.global.auth.handler.MemberAuthenticationFailureHandler;
import com.preproject.server.global.auth.handler.MemberAuthenticationSuccessHandler;
import com.preproject.server.global.auth.jwt.JwtTokenizer;
import com.preproject.server.global.auth.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .accessDeniedHandler(new MemberAccessDeniedHandler()) // 인증 실패시 처리
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeRequests(authorize-> authorize
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        // JWT 를 구현하기 위해 만든 필터를 추가함 + 로그인 시 Url을 설정해줌
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);

            jwtAuthenticationFilter.setFilterProcessesUrl("/members/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, customAuthorityUtils);

            builder
                    .addFilter(corsFilter)
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
