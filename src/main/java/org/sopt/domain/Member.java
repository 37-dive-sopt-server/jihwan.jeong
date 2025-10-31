package org.sopt.domain;

import java.time.LocalDate;

public class Member {
    private final Long id;
    private final String name;
    private final String email;
    private final Gender gender;
    private final String birthdate;

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
        if(age<20) throw new IllegalArgumentException("입력값이 올바르지 않습니다.");
    }
}
