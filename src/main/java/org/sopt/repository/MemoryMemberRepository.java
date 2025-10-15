package org.sopt.repository;

import org.sopt.domain.Member;

import java.time.LocalDate;
import java.util.*;

public class MemoryMemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private final FileMemberRepository fileMemberRepository;
    private static long sequence = 1L;

    public MemoryMemberRepository(FileMemberRepository fileMemberRepository) {
        this.fileMemberRepository = fileMemberRepository;
        loadFile(this.fileMemberRepository);
        setSequence();
    }

    public Long nextId() {
        return sequence;
    }

    private static void setSequence() {
        if (!store.isEmpty()) {
            sequence = store.keySet().stream()
                    .max(Long::compare)
                    .orElse(0L) + 1L;
        }
    }

    private static void loadFile(FileMemberRepository fileMemberRepository) {
        List<Member> members = fileMemberRepository.load();
        for (Member member : members) {
            store.put(member.getId(), member);
        }
    }

    public Member save(Member member) {
        store.put(member.getId(), member);
        fileMemberRepository.save(member);
        sequence++;
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
        fileMemberRepository.remove(id);
    }

    public boolean isExistEmail(String email) {
        return store.values().stream()
                .anyMatch(m -> m.getEmail().equals(email));
    }

    public boolean isExistId(Long id) {
        return store.containsKey(id);
    }
}
