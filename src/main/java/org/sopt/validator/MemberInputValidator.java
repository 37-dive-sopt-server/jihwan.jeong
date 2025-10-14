package org.sopt.validator;

import java.time.LocalDate;
import java.time.YearMonth;

public class MemberInputValidator {

    public void validateChoice(String choice) {
        String choiceRegex = "^[1-5]$";
        if(choice == null || choice.isEmpty() || !choice.matches(choiceRegex)) {
            throw new IllegalArgumentException("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
        }
    }

    public void validateName(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
    }

    public void validateEmail(String email) {
        String emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        if(email == null || email.isEmpty() || !email.matches(emailRegex)) {
            throw new IllegalArgumentException("이메일 형식에 맞게 입력해주세요.");
        }
    }

    public void validateGender(String gender) {
        if(gender == null || gender.isEmpty() || !(gender.equals("male") || gender.equals("female"))) {
            throw new IllegalArgumentException("성별은 male, female 중 하나입니다.");
        }
    }

    public void validateBirthDate(String birthDate) {
        String birthDateRegex = "^\\d{8}$";
        int year = Integer.parseInt(birthDate.substring(0, 4));
        int month = Integer.parseInt(birthDate.substring(4,6));
        int day = Integer.parseInt(birthDate.substring(6,8));
        if(month < 1 || month > 12 || day < 1 || day > YearMonth.of(year, month).lengthOfMonth() || birthDate.length() != 8 || !birthDate.matches(birthDateRegex)) {
            throw new IllegalArgumentException("생년월일을 형식에 맞게 입력해주세요. ex. 20000101");
        }
    }

    public void validateId(String id) {
        String idRegex = "^[1-9]\\d*$";
        if(id == null || id.isEmpty() || !id.matches(idRegex)) {
            throw new IllegalArgumentException("Id는 양의 정수 입니다 다시 입력해주세요.");
        }
    }
}
