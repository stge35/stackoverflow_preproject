package com.preproject.server.domain.member.entity;


import com.preproject.server.global.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String nickName;
    private String password;


    // 멤버 권한
    @ElementCollection(fetch = FetchType.EAGER) // ElementCollection 으로 별도의 테이블 생성해서 저장
    private List<String> roles = new ArrayList<>();
}
