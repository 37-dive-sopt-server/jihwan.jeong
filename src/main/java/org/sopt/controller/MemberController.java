package org.sopt.controller;

import org.sopt.dto.MemberRequestDto;
import org.sopt.global.ApiResponse;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(
            value = "/members"
    )
    public ResponseEntity<?> createMember(
            @RequestBody MemberRequestDto.Join joinDto
    ) throws Exception {
        System.out.println("joinDto.getName() = " + joinDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201,"회원 가입 성공", memberService.join(joinDto)));
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<?> findMemberById(
            @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,"회원 조회 성공",memberService.findOne(id)));
    }

    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>getAllMembers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,"회원 조회 성공",memberService.findAllMembers()));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> deleteMemberById(
            @PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200,"회원 삭제 성공", null));
    }
}
