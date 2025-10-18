package org.sopt.view;

import org.sopt.validator.MemberInputValidator;

import java.util.Scanner;

public class MemberInputView {

    private final MemberInputValidator memberInputValidator;
    public MemberInputView(MemberInputValidator memberInputValidator) {
        this.memberInputValidator = memberInputValidator;
    }

    Scanner scanner = new Scanner(System.in);

    public String inputMain() {

        while(true) {
            try {
                System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
                System.out.println("---------------------------------");
                System.out.println("1️⃣. 회원 등록 ➕");
                System.out.println("2️⃣. ID로 회원 조회 🔍");
                System.out.println("3️⃣. 전체 회원 조회 📋");
                System.out.println("4️⃣. ID로 회원 삭제");
                System.out.println("5️⃣. 종료 🚪");

                System.out.println("---------------------------------");
                System.out.print("메뉴를 선택하세요: ");

                String choice = scanner.nextLine();
                memberInputValidator.validateChoice(choice);

                return choice;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String inputName() {

        while(true) {
            try {
                System.out.print("등록할 회원 이름을 입력하세요: ");
                String name = scanner.nextLine();
                memberInputValidator.validateName(name);
                return name;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String inputEmail() {

        while(true) {
            try {
                System.out.print("등록할 회원 이메일을 입력하세요: ");
                String email = scanner.nextLine();
                memberInputValidator.validateEmail(email);
                return email;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String inputGender() {

        while(true) {
            try{
                System.out.print("등록할 회원 성별을 입력하세요(male, female): ");
                String gender = scanner.nextLine();
                memberInputValidator.validateGender(gender);
                return gender;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String inputBirthDate() {
        while(true) {
            try{
                System.out.print("등록할 회원 생년월일을 입력하세요.(ex, 20000101): ");
                String birthdate = scanner.nextLine();
                memberInputValidator.validateBirthDate(birthdate);
                return birthdate;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Long inputFindMemberId() {
        while(true) {
            try{
                System.out.print("조회할 회원 ID를 입력하세요: ");
                String id = scanner.nextLine();
                memberInputValidator.validateId(id);
                return Long.parseLong(id);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Long inputDeleteMemberId() {
        while(true) {
            try{
                System.out.print("삭제할 회원 ID를 입력하세요: ");
                String id = scanner.nextLine();
                memberInputValidator.validateId(id);
                return Long.parseLong(id);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
