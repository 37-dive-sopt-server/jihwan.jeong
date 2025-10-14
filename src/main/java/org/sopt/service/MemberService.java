package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.MemberDto;
import org.sopt.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemoryMemberRepository memoryMemberRepository;

    public MemberService(MemoryMemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }

    public Long join(MemberDto.Join joinDto) throws Exception {
        String name = joinDto.getName();
        String email = joinDto.getEmail();
        Gender gender = Gender.fromString(joinDto.getGender());
        String birthdate = joinDto.getBirthdate();

        if(memoryMemberRepository.isExistEmail(email)) throw new Exception("이미 존재하는 이메일입니다.");

        Member member = memoryMemberRepository.save(
                new Member(
                        memoryMemberRepository.nextId(),
                        name,
                        email,
                        gender,
                        birthdate
                )
        );

        return member.getId();
    }

    public Optional<Member> findOne(Long memberId) {
        return memoryMemberRepository.findById(memberId);
    }

    public List<Member> findAllMembers() {
        return memoryMemberRepository.findAll();
    }

    public void deleteMember(Long id) {
        memoryMemberRepository.deleteById(id);
    }
}
