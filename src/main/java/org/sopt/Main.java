package org.sopt;

import org.sopt.controller.MemberController;
import org.sopt.domain.Member;
import org.sopt.dto.MemberDto;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.service.MemberService;
import org.sopt.validator.MemberInputValidator;
import org.sopt.view.MemberInputView;
import org.sopt.view.MemberView;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        MemberInputValidator memberInputValidator = new MemberInputValidator();
        MemberInputView memberInputView = new MemberInputView(memberInputValidator);
        MemberView memberView = new MemberView(memberInputView);
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        MemberService memberService = new MemberService(memberRepository);
        MemberController memberController = new MemberController(memberService);

        while (true) {

            String choice = memberView.showMain();
            try {
                switch (choice) {
                    case "1": {
                        MemberDto.Join joinDto = memberView.showJoinMember();
                        Long createdId = memberController.createMember(joinDto);
                        memberView.showJoinResult(createdId);
                        break;
                    }
                    case "2": {
                        Long id = memberView.showFindMemberById();
                        Optional<Member> foundMember = memberController.findMemberById(id);
                        memberView.showFindMemberByIdResult(foundMember);
                        break;
                    }
                    case "3": {
                        List<Member> allMembers = memberController.getAllMembers();
                        memberView.showGetAllMembersResult(allMembers);
                        break;
                    }
                    case "4": {
                        Long id = memberView.showDeleteMemberById();
                        memberController.deleteMemberById(id);
                        memberView.showDeleteMemberByIdResult();
                        break;
                    }
                    case "5":
                        memberView.showExit();
                        return;
                }
            } catch (Exception e) {
                memberView.showError(choice, e);
            }
        }
    }
}