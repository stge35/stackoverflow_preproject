package com.preproject.server.domain.member.dto.response;

import com.preproject.server.domain.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseMember {

    private Long memberId;
    private String email;
    private String nickName;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public static ResponseMember from(MemberDto dto){
        return new ResponseMember(
                dto.getMemberId(),
                dto.getEmail(),
                dto.getNickName(),
                dto.getPassword(),
                dto.getCreateAt(),
                dto.getModifiedAt()
        );
    }
}
