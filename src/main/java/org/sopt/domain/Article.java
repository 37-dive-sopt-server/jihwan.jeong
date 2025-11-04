package org.sopt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Tag tag;
    private LocalDate createdAt;

    @Column(unique = true)
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public Article(String tag, LocalDate createdAt, String title, String content, Member member) {
        this.tag = Tag.valueOf(tag);
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.member = member;
    }
}