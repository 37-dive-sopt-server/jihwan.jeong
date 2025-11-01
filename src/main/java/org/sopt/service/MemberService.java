package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.MemberRequestDto;
import org.sopt.dto.MemberResponseDto;
import org.sopt.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemoryMemberRepository memoryMemberRepository;

    public MemberService(MemoryMemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }

    public MemberResponseDto.Member join(MemberRequestDto.Join joinDto) throws Exception {
        String name = joinDto.getName();
        String email = joinDto.getEmail();
        Gender gender = Gender.fromString(joinDto.getGender());
        String birthdate = joinDto.getBirthdate();

        if(memoryMemberRepository.isExistEmail(email)) throw new IllegalArgumentException("이미 가입된 이메일입니다.");

        Member member = memoryMemberRepository.save(
                new Member(
                        memoryMemberRepository.nextId(),
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
        Member member = memoryMemberRepository.findById(memberId).get();
        return new MemberResponseDto.Member(
                member.getId(),
                member.getName(),
                member.getBirthdate(),
                member.getEmail(),
                member.getGender()
        );
    }

    public List<MemberResponseDto.Member> findAllMembers() {
        return memoryMemberRepository.findAll().stream()
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
        if(!memoryMemberRepository.isExistId(id)) throw new IllegalArgumentException("존재하지 않은 회원 ID입니다.");
        memoryMemberRepository.deleteById(id);
    }
}
