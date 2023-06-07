package com.preproject.server.domain.member.service;

import com.preproject.server.domain.member.dto.MemberDto;
import com.preproject.server.domain.member.entity.Member;
import com.preproject.server.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입

}
