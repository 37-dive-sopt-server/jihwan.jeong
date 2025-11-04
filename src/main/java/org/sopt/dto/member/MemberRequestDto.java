package org.sopt.dto.member;


public class MemberRequestDto {

    public static class Join{
        private String name;
        private String email;
        private String gender;
        private String birthdate;

        public Join() {}
        public Join(String name, String email, String gender, String birthdate) {
            this.name = name;
            this.email = email;
            this.gender = gender;
            this.birthdate = birthdate;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setBirthdate(String birthdate) {
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
