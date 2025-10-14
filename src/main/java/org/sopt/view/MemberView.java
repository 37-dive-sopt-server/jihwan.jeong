package org.sopt.view;

import org.sopt.domain.Member;
import org.sopt.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public class MemberView {

    private final MemberInputView memberInputView;

    public MemberView(MemberInputView memberInputView) {
        this.memberInputView = memberInputView;
    }

    public String showMain() {
        return memberInputView.inputMain();
    }

    public MemberDto.Join showJoinMember() {
        String name = memberInputView.inputName();
        String email = memberInputView.inputEmail();
        String gender = memberInputView.inputGender();
        String birthdate = memberInputView.inputBirthDate();

        return new MemberDto.Join(name, email, gender, birthdate);
    }

    public Long showFindMemberById() {
        return memberInputView.inputFindMemberId();
    }

    public Long showDeleteMemberById() {
        return memberInputView.inputDeleteMemberId();
    }

    public void showExit() {
        System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
        memberInputView.close();
    }

    public void showError(String choice, Exception e) {
        switch (choice) {
            case "1" :
                System.out.print("❌ 회원 등록 실패: ");
                break;
            case "2" :
                System.out.print("❌ 회원 조회 실패: ");
                break;
            case "3" :
                System.out.print("❌ 전체 회원 조회 실패: ");
                break;
            case "4" :
                System.out.print("❌ 회원 삭제 실패: ");
                break;
        }
        System.out.println(e.getMessage());
    }

    public void showJoinResult(Long createdId) {
        System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
    }

    public void showFindMemberByIdResult(Optional<Member> foundMember) throws Exception {
        if (foundMember.isPresent()) {
            System.out.println("✅ 조회된 회원: ID=" + foundMember.get().getId() + ", 이름=" + foundMember.get().getName());
        }
        else throw new Exception("해당 ID의 회원을 찾을 수 없습니다.");
    }

    public void showGetAllMembersResult(List<Member> allMembers) {
        if(allMembers.isEmpty()) {
            System.out.println("ℹ️ 등록된 회원이 없습니다.");
            return;
        }
        System.out.println("--- 📋 전체 회원 목록 📋 ---");
        for (Member member : allMembers) {
            System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName());
        }
        System.out.println("--------------------------");
    }

    public void showDeleteMemberByIdResult() {
        System.out.println("회원 삭제가 완료되었습니다.");
    }
}
