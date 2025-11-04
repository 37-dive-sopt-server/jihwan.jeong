package org.sopt.dto.article;

public class ArticleRequestDto {
    public record create(
            Long memberId,
            String tag,
            String title,
            String content
    ){}

    public record findArticleByUserId(Long userId) {}
    public record findArticleByArticleId(Long articleId) {}
}
