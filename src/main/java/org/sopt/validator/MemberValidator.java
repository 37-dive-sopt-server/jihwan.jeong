package org.sopt.validator;

public class MemberValidator {
    public void validateJoin(String name, String email, String gender, String birthdate) {
        if(!(gender.equals("male")||gender.equals("female"))) {
            throw new IllegalArgumentException("성별은 male, female 중 하나입니다.");
        }
    }
}
