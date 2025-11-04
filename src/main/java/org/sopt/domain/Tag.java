package org.sopt.domain;

public enum Tag {
    CS,
    DB,
    SPRING,
    ETC;

    public static Tag fromString(String tag) {
        try{
            return Tag.valueOf(tag);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("태그는 CS, DB, SPRING, ETC 중 하나입니다.");
        }
    }
}
