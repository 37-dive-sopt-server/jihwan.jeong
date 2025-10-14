package org.sopt.dto;

public class MemberDto {

    public static class Join{
        private String name;
        private String email;
        private String gender;
        private String birthdate;

        public Join(String name, String email, String gender, String birthdate) {
            this.name = name;
            this.email = email;
            this.gender = gender;
            this.birthdate = birthdate;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getGender() {
            return gender;
        }

        public String getBirthdate() {
            return birthdate;
        }

    }
}
