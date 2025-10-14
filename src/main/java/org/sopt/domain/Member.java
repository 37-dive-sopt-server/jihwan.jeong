package org.sopt.domain;

import java.time.LocalDate;

public class Member {
    private Long id;
    private String name;
    private String email;
    private Gender gender;
    private String birthdate;

    public Member(Long id, String name, String email, Gender gender, String birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        isAge20Over(birthdate);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender.getGender();
    }

    public String getBirthdate() {
        return birthdate;
    }

    private void isAge20Over(String birthdate) {
        int age = LocalDate.now().getYear()-Integer.parseInt(birthdate.substring(0,4))+1;
        if(age<20) throw new IllegalArgumentException("20세 미만의 회원은 가입할 수 없습니다.");
    }
}
