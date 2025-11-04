package org.sopt.dto.member;

public class MemberResponseDto {
    public static class Member{
        private Long id;
        private String name;
        private String birthDate;
        private String email;
        private String gender;

        public Member(Long id, String name, String birthDate, String email, String gender) {
            this.id = id;
            this.name = name;
            this.birthDate = birthDate;
            this.email = email;
            this.gender = gender;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getEmail() {
            return email;
        }

        public String getGender() {
            return gender;
        }
    }
}
