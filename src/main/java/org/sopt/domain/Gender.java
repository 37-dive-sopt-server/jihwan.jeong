package org.sopt.domain;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public static Gender fromString(String gender) {
        for(Gender g : Gender.values()) {
            if(g.gender.equals(gender)) {
                return g;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 성별입니다. ");
    }

    public String getGender() {
        return gender;
    }
}
