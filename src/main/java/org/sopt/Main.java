package org.sopt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
//
//        MemberInputValidator memberInputValidator = new MemberInputValidator();
//        MemberInputView memberInputView = new MemberInputView(memberInputValidator);
//        MemberView memberView = new MemberView(memberInputView);
//
//        FileMemberRepository fileMemberRepository = new FileMemberRepository();
//        MemoryMemberRepository memberRepository = new MemoryMemberRepository(fileMemberRepository);
//        MemberService memberService = new MemberService(memberRepository);
//        MemberController memberController = new MemberController(memberService);
//
//        while (true) {
//
//            String choice = memberView.showMain();
//            try {
//                switch (choice) {
//                    case "1": {
//                        MemberDto.Join joinDto = memberView.showJoinMember();
//                        Long createdId = memberController.createMember(joinDto);
//                        memberView.showJoinResult(createdId);
//                        break;
//                    }
//                    case "2": {
//                        Long id = memberView.showFindMemberById();
//                        Optional<Member> foundMember = memberController.findMemberById(id);
//                        memberView.showFindMemberByIdResult(foundMember);
//                        break;
//                    }
//                    case "3": {
//                        List<Member> allMembers = memberController.getAllMembers();
//                        memberView.showGetAllMembersResult(allMembers);
//                        break;
//                    }
//                    case "4": {
//                        Long id = memberView.showDeleteMemberById();
//                        memberController.deleteMemberById(id);
//                        memberView.showDeleteMemberByIdResult();
//                        break;
//                    }
//                    case "5":
//                        memberView.showExit();
//                        fileMemberRepository.interrupt();
//                        return;
//                }
//            } catch (Exception e) {
//                memberView.showError(choice, e);
//            }
//        }
//    }
}