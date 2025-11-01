package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.MemberRequestDto;
import org.sopt.dto.MemberResponseDto;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto.Member join(MemberRequestDto.Join joinDto) throws Exception {
        String name = joinDto.getName();
        String email = joinDto.getEmail();
        Gender gender = Gender.fromString(joinDto.getGender());
        String birthdate = joinDto.getBirthdate();

        if(memberRepository.existsByEmail(email)) throw new IllegalArgumentException("이미 가입된 이메일입니다.");

        Member member = memberRepository.save(
                new Member(
                        name,
                        email,
                        gender,
                        birthdate
                )
        );

        return new MemberResponseDto.Member(
                member.getId(),
                member.getName(),
                member.getBirthdate(),
                member.getEmail(),
                member.getGender()
        );
    }

    public MemberResponseDto.Member findOne(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return new MemberResponseDto.Member(
                member.getId(),
                member.getName(),
                member.getBirthdate(),
                member.getEmail(),
                member.getGender()
        );
    }

    public List<MemberResponseDto.Member> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(
                        member -> new MemberResponseDto.Member(
                                member.getId(),
                                member.getName(),
                                member.getBirthdate(),
                                member.getEmail(),
                                member.getGender()
                        )
                )
                .toList();
    }

    public void deleteMember(Long id) {
        if(!memberRepository.existsById(id)) throw new IllegalArgumentException("존재하지 않은 회원 ID입니다.");
        memberRepository.deleteById(id);
    }
}
