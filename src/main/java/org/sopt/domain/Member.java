package org.sopt.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Gender gender;
    private String birthdate;

    @OneToMany(mappedBy = "member")
    private List<Article> articles = new ArrayList<>();

    public Member(String name, String email, Gender gender, String birthdate) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public Member() {

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
        return gender.toString();
    }

    public String getBirthdate() {
        return birthdate;
    }

    private void isAge20Over(String birthdate) {
        int age = LocalDate.now().getYear()-Integer.parseInt(birthdate.substring(0,4))+1;
        if(age<20) throw new IllegalArgumentException("입력값이 올바르지 않습니다.");
    }
}
