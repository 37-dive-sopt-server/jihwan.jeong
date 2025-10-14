package org.sopt.repository;

import org.sopt.domain.Member;

import java.time.LocalDate;
import java.util.*;

public class MemoryMemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();

    public Member save(Member member) {
        boolean emailExists = store.values().stream()
                .anyMatch(m -> m.getEmail().equals(member.getEmail()));

        if (emailExists) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        int age = LocalDate.now().getYear()-Integer.parseInt(member.getBirthdate().substring(0,4))+1;
        if(age<20) throw new IllegalArgumentException("20세 미만의 회원은 가입할 수 없습니다.");

        store.put(member.getId(), member);

        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}
