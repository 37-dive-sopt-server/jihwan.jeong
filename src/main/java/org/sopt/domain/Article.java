package org.sopt.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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
}