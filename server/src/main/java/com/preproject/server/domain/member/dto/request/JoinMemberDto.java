package com.preproject.server.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinMemberDto {
    private String email;
    private String nickName;
    private String password;
}
