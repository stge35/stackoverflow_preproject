package com.preproject.server.global.auth.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthorityUtils {

    @Value("${mail.address.admin}")
    private String adminMailAddress;

    private final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER"); // Admin 의 Role 에 admin, user 권한
    private final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    // memory 상의 Role 을 기반으로 권한 정보를 생성
    public  List<GrantedAuthority> createAuthorities(String email) {
        if(email.equals(adminMailAddress)) {
            return ADMIN_ROLES;
        }
        else {
            return USER_ROLES;
        }
    }

    // DB 에 저장된 Role 을 기반으로 권한 정보를 생성
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    // DB 저장용
    public List<String> createRoles(String email) {
        if(email.equals(adminMailAddress)) {
            return ADMIN_ROLES_STRING;
        }
        else{
            return USER_ROLES_STRING;
        }
    }

    // 관리자 확인
    public Boolean checkAdmin(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        return authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}
