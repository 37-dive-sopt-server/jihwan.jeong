package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.dto.MemberDto;
import org.sopt.service.MemberService;

import java.util.List;
import java.util.Optional;

public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public Long createMember(MemberDto.Join joinDto) throws Exception {
        return memberService.join(joinDto);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    public void deleteMemberById(Long id) {
        memberService.deleteMember(id);
    }
}
