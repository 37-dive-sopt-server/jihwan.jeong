package org.sopt.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.Article;
import org.sopt.domain.Member;
import org.sopt.dto.article.ArticleRequestDto;
import org.sopt.dto.article.ArticleResponseDto;
import org.sopt.repository.ArticleRepository;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleResponseDto.ArticleInfo findArticleByArticleId(ArticleRequestDto.findArticleByArticleId requestDto) {
        Long articleId = requestDto.articleId();
        Article article = articleRepository.findById(articleId).orElse(null);
        return ArticleResponseDto.ArticleInfo.from(article);
    }

    public ArticleResponseDto.ArticleInfos findAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return ArticleResponseDto.ArticleInfos.from(articles);
    }

    public ArticleResponseDto.ArticleInfo createArticle(ArticleRequestDto.create requestDto) {
        Long memberId = requestDto.memberId();
        String tag = requestDto.tag();
        String title = requestDto.title();
        String content = requestDto.content();

        Member member = memberRepository.findById(memberId).orElse(null);

        Article article = new Article(tag, LocalDate.now(), title, content, member);
        articleRepository.save(article);

        return ArticleResponseDto.ArticleInfo.from(article);
    }
}
