package org.sopt.domain;

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
}
