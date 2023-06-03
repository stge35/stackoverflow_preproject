package com.preproject.server.domain.member.dto;

import com.preproject.server.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberDto {

    private Long memberId;
    private String email;
    private String nickName;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getNickName(),
                entity.getPassword(),
                entity.getCreateAt(),
                entity.getModifiedAt()
        );
    }
}
